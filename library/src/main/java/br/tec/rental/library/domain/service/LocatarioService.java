package br.tec.rental.library.domain.service;

import br.tec.rental.library.api.dto.locatario.LocatarioResponse;
import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.LocatarioMapping;
import br.tec.rental.library.domain.model.Aluguel;
import br.tec.rental.library.domain.model.Locatario;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.LocatarioRepository;
import br.tec.rental.library.domain.service.validator.ValidaDadosLocatarioJaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class LocatarioService {

    private LocatarioRepository locatarioRepository;
    private LocatarioMapping locatarioMapping;
    private ValidaDadosLocatarioJaExiste validaDadosLocatarioJaExiste;
    private AluguelRepository aluguelRepository;

    @Autowired
    public LocatarioService(LocatarioRepository locatarioRepository, LocatarioMapping locatarioMapping,
                            ValidaDadosLocatarioJaExiste validaDadosLocatarioJaExiste,
                            AluguelRepository aluguelRepository
    ) {
        this.locatarioRepository = locatarioRepository;
        this.locatarioMapping = locatarioMapping;
        this.validaDadosLocatarioJaExiste = validaDadosLocatarioJaExiste;
        this.aluguelRepository = aluguelRepository;
    }

    public List<LocatarioResponse> getAll() {
        return locatarioMapping.toResponseList(locatarioRepository.findAll());
    }

    public Optional<Locatario> getOneById(Long id) {
        return locatarioRepository.findById(id);
    }
    
    public Locatario save(Locatario locatario) {
        validaDadosLocatarioJaExiste.execute(locatario.getId(), locatario.getCpf(), locatario.getEmail());
        return locatarioRepository.save(locatario);
    }

    public void deleteOneById(Long id) {
        List<Aluguel> alugueis = aluguelRepository.findByLocatarioId(id);

        if(!alugueis.isEmpty()) {
            boolean isDevolucaoPendente = alugueis.stream()
                    .anyMatch(aluguel -> aluguel.getDevolucao() == null);

            if(isDevolucaoPendente) throw new ConflictException("Locatário possui devolução pendente!");

            aluguelRepository.deleteAll(alugueis);
        }

        locatarioRepository.deleteById(id);
    }
}
