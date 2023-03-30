package repositorio;

import modelo.Emprestimo;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

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
    public void updateOne(String codigo, Emprestimo novoEmprestimo) {
        Emprestimo emprestimoAtual = null;

        for (int i = 0; i < catalogoEmprestimos.size(); i++) {
            if (catalogoEmprestimos.get(i).getCodigo().equals(codigo)) {
                emprestimoAtual = catalogoEmprestimos.get(i);
                break;
            }
        }

        if (emprestimoAtual == null) throw new RuntimeException("Codigo inválido");

        Collections.replaceAll(catalogoEmprestimos, emprestimoAtual, novoEmprestimo);
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
        boolean isSuccess = catalogoEmprestimos
                .removeIf(e -> e.getCodigo() == codigo);
        if(!isSuccess) throw new RuntimeException("Codigo inválido");
    }

    @Override
    public Optional<Emprestimo> getByIsbnLivro(String isbn) {
        for (int i = 0; i < catalogoEmprestimos.size(); i++)
            if(catalogoEmprestimos.get(i).getLivro().getIsbn().equals(isbn))
                return Optional.of(catalogoEmprestimos.get(i));
        return Optional.empty();
    }

}
