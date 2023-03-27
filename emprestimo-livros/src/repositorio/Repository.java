package repositorio;

import java.util.List;
import java.util.Optional;

public interface Repository<T, I> {
    void addOne(T entity);
    List<T> getAll();
    Optional<T> getOne(I identifier);
    void deleteOne(I identifier);
}
