import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class NotaAlunoTest {

    private NotaAluno not = new NotaAluno();
    private List<Double> listaDeNotas = Arrays.asList(7.0, 7.2, 6.6, 7.8);

    @Test
    void deveRetornarMediaDasNotas() {
        assertEquals(7.15, not.calculaMedia(listaDeNotas));
    }

    @Test
    void deveRetornarAprovadoParaMediaAcimaSeis() {
        Double media = 7.5;
        assertEquals("Aprovado", not.situacao(media));
    }

    @Test
    void deveRetornarSituacaoSuplementarParaMediaEntreQuatroSeis() {
        Double media1 = 4.0, media2 = 6.0, media3 = 6.1;
        assertEquals("Verificação Suplementar", not.situacao(media1));
        assertEquals("Verificação Suplementar", not.situacao(media2));
        assertNotEquals("Verificação Suplementar", not.situacao(media3));
    }

    @Test
    void deveRetornarReprovadoParaMediaAbaixoQuatro() {
        Double media1 = 3.9, media2 = 4.0;
        assertEquals("Reprovado", not.situacao(media1));
        assertNotEquals("Reprovado", not.situacao(media2));
    }

}
