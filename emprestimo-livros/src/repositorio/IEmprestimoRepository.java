package repositorio;

import modelo.Emprestimo;

import java.util.Optional;

public interface IEmprestimoRepository extends Repository<Emprestimo, String> {
    Optional<Emprestimo> getByIsbnLivro(String isbn);
}
