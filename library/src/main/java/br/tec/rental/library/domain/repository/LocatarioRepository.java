package br.tec.rental.library.domain.repository;

import br.tec.rental.library.domain.model.Locatario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LocatarioRepository extends JpaRepository<Locatario, Long> {
    Optional<Locatario> findByCpf(String cpf);
    Optional<Locatario> findByEmail(String email);
}
