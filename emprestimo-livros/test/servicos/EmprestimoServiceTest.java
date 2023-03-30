package servicos;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import repositorio.EmprestimoRepository;
import repositorio.IEmprestimoRepository;
import repositorio.LivroRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.*;

public class EmprestimoServiceTest {

    private EmprestimoService emprestimoService;
    private IEmprestimoRepository emprestimoRepository;
    private List<Emprestimo> emprestimosCatalogo;
    private Livro livro1;
    private Livro livro2;
    private Pessoa pessoa1;

    @BeforeEach
    void initSetup() {

        livro1 = new Livro("Livro1", "11111", "editora1", "autor1");
        livro2 = new Livro("Livro2", "22222", "editora2", "autor2");
        pessoa1 = new Pessoa("pesso1", "123456", LocalDate.of(1990, 2, 10), "987654321");

        emprestimoRepository = new EmprestimoRepository(
                new ArrayList<>(
                        List.of(
                                new Emprestimo(
                                        "11111",
                                        livro1,
                                        pessoa1,
                                        LocalDate.of(2023, 3, 30),
                                        LocalDate.of(2023, 4, 4)
                                )
                        )
                )
        );

        LivroRepository livroRepository = new LivroRepository(
                new ArrayList<>(
                        Arrays.asList(
                                livro1,
                                new Livro("Livro2", "22222", "editora2", "autor2"),
                                new Livro("Livro3", "33333", "editora3", "autor3")
                        )
                )
        );

        LivroService livroService = new LivroService(livroRepository, emprestimoRepository);

        emprestimoService = new EmprestimoService(livroService, emprestimoRepository);

        emprestimosCatalogo = emprestimoRepository.getAll();
    }

    @Test
    void emprestarDeveLancarExcecaoParaLivroJaEmprestado() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> emprestimoService.emprestar(
                        livro1,
                        pessoa1,
                        LocalDate.of(2023, 3, 31)
                       )
        );

        assertEquals("Livro não disponível para emprestimo", ex.getMessage());
    }

    @Test
    void emprestarDeveRealizarNovoEmprestimo() {
        assertThat(emprestimosCatalogo)
                .hasSize(1)
                .extracting(Emprestimo::getLivro)
                .contains(livro1)
                .doesNotContain(livro2);

        emprestimoService.emprestar(livro2, pessoa1, LocalDate.of(2023, 3, 31));

        assertThat(emprestimosCatalogo)
                .hasSize(2)
                .extracting(Emprestimo::getLivro)
                .contains(livro2)
                .contains(livro1);
    }

    @Test
    void darBaixaDeveLancarExcecaoAoPassarCodigoInvalido() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> emprestimoService.darBaixa("00000", LocalDate.now())
        );

        assertEquals("Erro na operação: Codigo inválido", ex.getMessage());
    }

    @Test
    void darBaixaDeveRetornarTrueERealizarABaixa() {
        assertThat(emprestimosCatalogo)
                .hasSize(1);

        Emprestimo emprestimoAtual = emprestimoRepository.getOne("11111").get();

        assertNull(emprestimoAtual.getData_devolucao());

        assertTrue(emprestimoService.darBaixa("11111", LocalDate.of(2023, 3, 30)));

        assertEquals(LocalDate.of(2023, 3, 30), emprestimoAtual.getData_devolucao());

        assertThat(emprestimosCatalogo)
                .hasSize(1);
    }

}
