package repositorio;

import modelo.Emprestimo;

import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {
    void addOne(T entity);
    void updateOne(I identifier, T newEntity);
    List<T> getAll();
    Optional<T> getOne(I identifier);
    void deleteOne(I identifier);
}
