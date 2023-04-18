package br.tec.atividade.agenda.controller;

import br.tec.atividade.agenda.dto.AtualizarContatoRequest;
import br.tec.atividade.agenda.model.Agenda;
import br.tec.atividade.agenda.model.Contato;
import br.tec.atividade.agenda.model.Endereco;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.jdbc.Sql;

import java.util.ArrayList;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@Sql("/insert.sql")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class AgendaControllerTest {

    @LocalServerPort
    private int port;
    private static final String URI_POST_NOVA = "/agenda/nova";
    private static final String URI_GET_TODAS = "/agenda";

    @BeforeEach
    void setup() {
        RestAssured.port = this.port;
    }

    @Test
    void getAgendaDeveRetornarAgendas() {
        given()
                .when()
                .get(URI_GET_TODAS)
                .then()

                .assertThat()
                .statusCode(200)
                .body("id", hasItems(1, 2), "size()", equalTo(2));
    }

    @Test
    void postNovaAgendaComDadosValidos() throws JsonProcessingException {
        Agenda agenda = new Agenda(null, new ArrayList<Contato>());
        String request = new ObjectMapper().writeValueAsString(agenda);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .post(URI_POST_NOVA)
                .then()

                .assertThat()
                .statusCode(201)
                .body("id", equalTo(3), "contato", hasSize(0));
    }

    @Test
    void putAtualizarAgendaDeveAtualizarOContatoDaAgenda() throws JsonProcessingException {
        AtualizarContatoRequest contatoAtualizado = new AtualizarContatoRequest(
                1L, "Contato", "atualizado", "9999999", "atualizado.mail",
                new Endereco(1L, "novo logradouro", "numero", "novo bairro", "nova cidade", "uf")
        );
        String request = new ObjectMapper().writeValueAsString(contatoAtualizado);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put("/agenda/1/atualizar-contato")
                .then()

                .assertThat()
                .statusCode(200)
                .body(  "nome", equalTo("Contato"),
                        "sobrenome", equalTo("atualizado"),
                        "telefone", equalTo("9999999"),
                        "email", equalTo("atualizado.mail")
                );
    }

    @Test
    void putAdicionarAgendaDeveAdicionarNovoContato() throws JsonProcessingException {
        Contato contatoAtualizado = new Contato(
                null, "contato", "novo", "77777777", "novo.mail",
                new Endereco(null, "novo logradouro", "numero", "novo bairro", "nova cidade", "uf")
        );
        String request = new ObjectMapper().writeValueAsString(contatoAtualizado);

        given()
                .contentType(ContentType.JSON)
                .body(request)
                .when()
                .put("/agenda/1/adicionar-contato")
                .then()

                .assertThat()
                .statusCode(200)
                .body(  "nome", equalTo("contato"),
                        "sobrenome", equalTo("novo"),
                        "telefone", equalTo("77777777"),
                        "email", equalTo("novo.mail")
                );
    }

    @Test
    void deleteAgendaContatoDeveDeletarContato() {
        given()
                .when()
                .delete("/agenda/contato/1")
                .then()

                .assertThat()
                .statusCode(204);
    }
}
