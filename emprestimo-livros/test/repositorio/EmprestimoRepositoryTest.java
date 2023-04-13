package repositorio;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class EmprestimoRepositoryTest {

    private IEmprestimoRepository emprestimoRepository;
    private List<Emprestimo> catalogoEmprestimos;
    private List<Emprestimo> catalogoDoRepository;

    private Emprestimo emprestimoNovo1;
    private Emprestimo emprestimoNovo2;

    @BeforeEach
    void init() {

        emprestimoNovo1 = new Emprestimo(
                "11111",
                new Livro("Livro Teste 1", "123456", "editora1", "autor1"),
                new Pessoa("Pessoa1", "1234567", LocalDate.of(1998, 3, 3), "99999999"),
                LocalDate.of(2023, 2, 10),
                LocalDate.of(2023, 2, 12)
        );

        emprestimoNovo2 = new Emprestimo(
                "33333",
                new Livro("Livro Teste 2", "345678", "editora2", "autor2"),
                new Pessoa("Pessoa2", "3456789", LocalDate.of(1978, 10, 6), "99999999"),
                LocalDate.of(2023, 2, 1),
                LocalDate.of(2023, 2, 7)
        );


        List<Emprestimo> emprestimos = new ArrayList<>(
                Arrays.asList(emprestimoNovo1, emprestimoNovo2)
        );


        catalogoEmprestimos = emprestimos;
        emprestimoRepository = new EmprestimoRepository(catalogoEmprestimos);

        catalogoDoRepository = emprestimoRepository.getAll();
    }

    @Test
    void deveAdicionarNovoEmprestimo() {

        assertThat(catalogoDoRepository)
                .hasSize(2)
                .extracting("codigo")
                .doesNotContain("222222");

        emprestimoRepository.addOne(
                new Emprestimo(
                        "222222",
                        new Livro("Livro Teste 3", "876543", "editora3", "autor3"),
                        new Pessoa("Pessoa3", "398764", LocalDate.of(1998, 3, 3), "11111111"),
                        LocalDate.of(2023, 2, 10),
                        LocalDate.of(2023, 2, 12)
                )
        );


        assertThat(catalogoDoRepository)
                .hasSize(3)
                .extracting("codigo")
                .contains("222222");
    }

    @Test
    void deveListarTodosEmprestimos() {
        assertThat(catalogoDoRepository)
                .hasSize(2)
                .isEqualTo(catalogoEmprestimos);
    }

    @Test
    void deveBuscarUmEmprestimoPeloCodigo() {
        Emprestimo emprestimo = emprestimoRepository.getOne("33333").get();
        assertThat(emprestimo)
                .usingRecursiveComparison()
                .isEqualTo(emprestimoNovo2);
    }

    @Test
    void deveRetornarVazioParaEmprestimoNaoEncontrado() {
       assertTrue(emprestimoRepository.getOne("00000").isEmpty());
    }

    @Test
    void deveAtualizarEmprestimo() {
        Emprestimo emprestimo = emprestimoRepository.getOne("33333").get();
        assertThat(emprestimo)
                .hasFieldOrPropertyWithValue("data_devolucao", null);

        emprestimo.setData_devolucao(LocalDate.of(2023, 3, 20));

        emprestimoRepository.updateOne("33333", emprestimo);

        assertThat(emprestimoRepository.getOne("33333").get())
                .hasFieldOrPropertyWithValue("data_devolucao", LocalDate.of(2023, 3, 20));

    }

    @Test
    void deveLancarExcecaoAoTentarAtualizarEmprestimoComCodigoInvalido() {
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> emprestimoRepository.updateOne("000000", emprestimoNovo2)
        );

        assertEquals("Codigo inválido", ex.getMessage());
    }

    @Test
    void deveBuscarEmprestimoPeloIsbnDoLivro() {
        Emprestimo emprestimoBuscado = emprestimoRepository.getByIsbnLivro("345678").get();

        assertThat(emprestimoBuscado)
                .usingRecursiveComparison()
                .isEqualTo(emprestimoNovo2);
    }

    @Test
    void deveRetornarVazioParaBuscaPorEmprestimoPeloIsbnInvalido() {
        assertTrue(emprestimoRepository.getByIsbnLivro("000000").isEmpty());
    }

    @Test
    void deveDeletarEmprestimoPeloCodigo() {
        assertThat(catalogoDoRepository)
                .hasSize(2)
                .extracting("codigo")
                .contains("11111");

        emprestimoRepository.deleteOne("11111");

        assertThat(catalogoDoRepository)
                .hasSize(1)
                .extracting("codigo")
                .doesNotContain("11111");

    }

    @Test
    void deveLancarExcecaoAoTentarDeletarEmprestimoInexistente() {
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> emprestimoRepository.deleteOne("000000")
        );

        assertEquals("Codigo inválido", ex.getMessage());
    }

}