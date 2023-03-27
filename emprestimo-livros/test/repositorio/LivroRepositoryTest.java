package repositorio;

import modelo.Livro;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

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

}
