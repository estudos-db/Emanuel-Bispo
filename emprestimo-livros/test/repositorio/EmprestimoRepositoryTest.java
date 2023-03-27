package repositorio;

import modelo.Emprestimo;
import modelo.Livro;
import modelo.Pessoa;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class EmprestimoRepositoryTest {

    private IEmprestimoRepository emprestimoRepository;
    private List<Emprestimo> catalogoEmprestimos;
    private List<Emprestimo> catalogoDoRepository;

    @BeforeEach
    void init() {
        List<Emprestimo> emprestimos = Arrays.asList(
                new Emprestimo(
                        "11111",
                        new Livro("Livro Teste 1", "123456", "editora1", "autor1"),
                        new Pessoa("Pessoa1", "1234567", LocalDate.of(1998, 3, 3), "99999999"),
                        LocalDate.of(2023, 2, 10),
                        LocalDate.of(2023, 2, 12)
                ),
                new Emprestimo(
                        "33333",
                        new Livro("Livro Teste 2", "345678", "editora2", "autor2"),
                        new Pessoa("Pessoa2", "3456789", LocalDate.of(1978, 10, 6), "99999999"),
                        LocalDate.of(2023, 2, 1),
                        LocalDate.of(2023, 2, 7)
                )
        );


        catalogoEmprestimos = emprestimos;
        emprestimoRepository = new EmprestimoRepository(catalogoEmprestimos);

        catalogoDoRepository = emprestimoRepository.getAll();
    }

    @Test
    void deveListarTodosEmprestimos() {
        assertThat(catalogoDoRepository)
                .hasSize(2)
                .isEqualTo(catalogoEmprestimos);
    }

    @Test
    void deveBuscarUmEmprestimoPeloCodigo() {

        String codigo = "33333";

        Emprestimo emprestimo = emprestimoRepository.getOne(codigo).get();

        assertThat(emprestimo)
                .hasFieldOrPropertyWithValue("codigo", codigo);

        assertThat(emprestimo.getPessoa())
                .hasFieldOrPropertyWithValue("nome", "Pessoa2");

        assertThat(emprestimo.getLivro())
                .hasFieldOrPropertyWithValue("titulo", "Livro Teste 2");
    }

}
