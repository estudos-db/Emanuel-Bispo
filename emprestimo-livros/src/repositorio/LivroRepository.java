package repositorio;

import modelo.Emprestimo;
import modelo.Livro;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LivroRepository implements Repository<Livro, String> {

    private List<Livro> catalogoLivros;

    public LivroRepository(List<Livro> catalogoLivro) {
        this.catalogoLivros = catalogoLivro;
    }

    @Override
    public void addOne(Livro livro) {
        catalogoLivros.add(livro);
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
        Livro livro =  catalogoLivros.stream()
                .filter(livr -> livr.getIsbn() == isbn)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Codigo inválido"));

        catalogoLivros.remove(livro);
    }

}
