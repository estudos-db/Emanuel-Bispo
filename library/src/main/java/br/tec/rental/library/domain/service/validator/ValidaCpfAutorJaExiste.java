package br.tec.rental.library.domain.service.validator;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.repository.AutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidaCpfAutorJaExiste {

    private AutorRepository autorRepository;

    @Autowired
    public ValidaCpfAutorJaExiste(AutorRepository autorRepository) {
        this.autorRepository = autorRepository;
    }

    public boolean execute(Long id, String cpf) {
        Autor autor = autorRepository.findByCpf(cpf).orElse(null);

        if (autor != null && autor.getId() != id)
            throw new ConflictException("CPF já registrado no sistema!");

        return true;
    }
}
