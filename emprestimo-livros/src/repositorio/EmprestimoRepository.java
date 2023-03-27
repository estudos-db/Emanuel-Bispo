package repositorio;

import modelo.Emprestimo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

public class EmprestimoRepository implements IEmprestimoRepository {

    private List<Emprestimo> catalogoEmprestimos;

    public EmprestimoRepository(List<Emprestimo> catalogoEmprestimos) {
        this.catalogoEmprestimos = catalogoEmprestimos;
    }

    @Override
    public void addOne(Emprestimo emprestimo) {
        catalogoEmprestimos.add(emprestimo);
    }

    @Override
    public List<Emprestimo> getAll() {
        return Collections.unmodifiableList(catalogoEmprestimos);
    }


    @Override
    public Optional<Emprestimo> getOne(String codigo) {
        for (int i = 0; i < catalogoEmprestimos.size(); i++)
            if(catalogoEmprestimos.get(i).getCodigo().equals(codigo))
                return Optional.of(catalogoEmprestimos.get(i));

        return Optional.empty();
    }

    @Override
    public void deleteOne(String codigo) {
        Emprestimo emp =  catalogoEmprestimos.stream()
                .filter(e -> e.getCodigo() == codigo)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Codigo inválido"));

        catalogoEmprestimos.remove(emp);
    }

    @Override
    public Optional<Emprestimo> getByIsbnLivro(String isbn) {
        for (int i = 0; i < catalogoEmprestimos.size(); i++)
            if(catalogoEmprestimos.get(i).getLivro().getIsbn().equals(isbn))
                return Optional.of(catalogoEmprestimos.get(i));
        return Optional.empty();
    }

}
