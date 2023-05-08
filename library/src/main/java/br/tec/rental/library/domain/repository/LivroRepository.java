package br.tec.rental.library.domain.repository;

import br.tec.rental.library.domain.model.Livro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LivroRepository extends JpaRepository<Livro, Long> {
    List<Livro> findAllByAutorId(Long id);

    List<Livro> findAllByAutorNome(String nome);

    @Query(value = "SELECT l FROM Aluguel a INNER JOIN a.livros l WHERE a.devolucao IS NULL")
    List<Livro> findAllUnavailableToRent();

    @Query(value = "SELECT l.* FROM livro l INNER JOIN aluguel_livro al INNER JOIN aluguel a WHERE l.id = al.livro_id AND a.locatario_id = :id GROUP BY l.id",
            nativeQuery = true)
    List<Livro> findAllByLocatarioRental(@Param(value = "id") Long id);
}
