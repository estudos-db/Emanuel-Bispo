package repositorio;

import modelo.Livro;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LivroRepository implements Repository<Livro, String> {

    private final List<Livro> catalogoLivros;

    public LivroRepository(List<Livro> catalogoLivro) {
        this.catalogoLivros = catalogoLivro;
    }

    @Override
    public void addOne(Livro livro) {
        catalogoLivros.add(livro);
    }

    @Override
    public void updateOne(String isbn, Livro novoLivro) {
        Livro livroAtual = null;

        for (Livro catalogoLivro : catalogoLivros) {
            if (catalogoLivro.getIsbn().equals(isbn)) {
                livroAtual = catalogoLivro;
                break;
            }
        }

        if (livroAtual == null) throw new RuntimeException("Isbn inválido");

        Collections.replaceAll(catalogoLivros, livroAtual, novoLivro);

    }

    @Override
    public List<Livro> getAll() {
        return Collections.unmodifiableList(catalogoLivros);
    }

    @Override
    public Optional<Livro> getOne(String isbn) {
        for (int i = 0; i < catalogoLivros.size(); i++)
            if(catalogoLivros.get(i).getIsbn().equals(isbn))
                return Optional.of(catalogoLivros.get(i));

        return Optional.empty();
    }

    @Override
    public void deleteOne(String isbn) {
        boolean isSuccess = catalogoLivros.removeIf(l -> l.getIsbn().equals(isbn));
        if (!isSuccess) throw new RuntimeException("Isbn inválido");
    }

}
