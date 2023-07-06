package br.tec.rental.library.domain.repository;

import br.tec.rental.library.domain.model.Autor;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AutorRepository extends JpaRepository<Autor, Long> {
    Optional<Autor> findByCpf(String cpf);
    Optional<Autor> findByNome(String nome);
}
