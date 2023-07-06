package br.tec.rental.library.domain.repository;

import br.tec.rental.library.domain.model.Aluguel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface AluguelRepository extends JpaRepository<Aluguel, Long> {
    List<Aluguel> findByLocatarioId(Long id);
    List<Aluguel> findByLivrosId(Long id);
}
