package repositorio;

import modelo.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

public class LivroRepositoryTest {

    private LivroRepository livroRepository;
    private List<Livro> catalogo;
    private List<Livro> livroRepositoryCatalogo;

    @BeforeEach
    void init() {
        catalogo = new ArrayList<>();
        catalogo.add(new Livro("Livro Teste 1", "123456", "editora1", "autor1"));
        catalogo.add(new Livro("Livro Teste 2", "234567", "editora2", "autor2"));

        livroRepository = new LivroRepository(catalogo);
        livroRepositoryCatalogo = livroRepository.getAll();
    }


    @Test
    void livroRepositoryDeveListarTodosLivros() {
        assertThat(livroRepositoryCatalogo)
                .hasSize(2)
                .isEqualTo(catalogo);
    }

    @Test
    void livroRepositoryDeveBuscarUmLivroPeloIsbn() {
        Livro livro = livroRepository.getOne("234567").get();
        assertThat(livro).hasFieldOrPropertyWithValue("titulo", "Livro Teste 2");
    }

    @Test
    void livroRepositoryDeveAtualizarLivro() {
        Livro livro = livroRepository.getOne("234567").get();
        assertThat(livro)
                .hasFieldOrPropertyWithValue("titulo", "Livro Teste 2")
                .hasFieldOrPropertyWithValue("autor", "autor2");

        livroRepository.updateOne(
                "234567",
                new Livro("novo titulo", livro.getIsbn(), "nova editora","novo autor")
        );

        assertThat(livroRepository.getOne("234567").get())
                .hasFieldOrPropertyWithValue("titulo", "novo titulo")
                .hasFieldOrPropertyWithValue("autor", "novo autor");

    }

    @Test
    void livroRepositoryDeveLancarExcecaoAoTentarAtualizarLivroInexistente() {
        RuntimeException ex = assertThrows(
                RuntimeException.class,
                () -> livroRepository.updateOne(
                        "000000",
                        new Livro("novo titulo", "novoisbn", "nova editora","novo autor")
                )
        );

        assertEquals("Isbn inválido", ex.getMessage());
    }

    @Test
    void livroRepositoryDeveRetornarVazioParaLivroAusenteNoCatalogo() {
        assertTrue(livroRepository.getOne("11111").isEmpty());
    }

    @Test
    void livroRepositoryDeveAdicionarLivroAoCatalogo() {
        Livro novoLivro = new Livro("Livro Teste 3", "345668", "editora3", "autor3");

        assertThat(livroRepositoryCatalogo).doesNotContain(novoLivro);

        livroRepository.addOne(novoLivro);
        assertThat(livroRepositoryCatalogo).contains(novoLivro);
    }

    @Test
    void livroRepositoryDeveDeletarLivroDoCatalogo() {
        String isbn = "234567";

        assertThat(livroRepositoryCatalogo)
                .hasSize(2)
                .extracting("isbn")
                .contains(isbn);

        livroRepository.deleteOne(isbn);

        assertThat(livroRepositoryCatalogo)
                .hasSize(1)
                .extracting("isbn")
                .doesNotContain(isbn);
    }

    @Test
    void livroRepositoryDeveLancarExcecaoAoTentarDeletarivroAusenteNoCatalogo() {
        RuntimeException exception = assertThrows(
                RuntimeException.class, () -> livroRepository.deleteOne("11111")
        );

        assertEquals("Isbn inválido", exception.getMessage());
    }

}
