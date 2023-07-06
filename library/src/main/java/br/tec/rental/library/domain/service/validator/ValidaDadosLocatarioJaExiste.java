package br.tec.rental.library.domain.service.validator;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.domain.model.Locatario;
import br.tec.rental.library.domain.repository.LocatarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaDadosLocatarioJaExiste {

    private LocatarioRepository locatarioRepository;

    @Autowired
    public ValidaDadosLocatarioJaExiste(LocatarioRepository locatarioRepository) {
        this.locatarioRepository = locatarioRepository;
    }

    public void execute(Long id, String cpf, String email) {
        Locatario locatarioEmailUsado = locatarioRepository.findByEmail(email).orElse(null);

        if (locatarioEmailUsado != null && locatarioEmailUsado.getId() != id) {
            throw new ConflictException("Email já registrado no sistema!");
        }

        Locatario locatarioCpflUsado = locatarioRepository.findByCpf(cpf).orElse(null);

        if (locatarioCpflUsado != null && locatarioCpflUsado.getId() != id) {
            throw new ConflictException("CPF já registrado no sistema!");
        }
    }

}
