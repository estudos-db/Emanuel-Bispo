package br.tec.rental.library.domain.service;

import br.tec.rental.library.api.dto.aluguel.AluguelCreatedResponse;
import br.tec.rental.library.api.dto.aluguel.AluguelRequest;
import br.tec.rental.library.api.exception.EntityNotFound;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.AluguelMapping;
import br.tec.rental.library.domain.model.Aluguel;
import br.tec.rental.library.domain.model.Livro;
import br.tec.rental.library.domain.model.Locatario;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.repository.LocatarioRepository;
import br.tec.rental.library.domain.service.validator.ValidaIDs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class AluguelService {

    private AluguelRepository aluguelRepository;
    private LocatarioRepository locatarioRepository;
    private LivroRepository livroRepository;
    private AluguelMapping aluguelMapping;
    private ValidaIDs validador;

    @Autowired
    public AluguelService(AluguelRepository aluguelRepository,
                          LocatarioRepository locatarioRepository,
                          LivroRepository livroRepository,
                          AluguelMapping aluguelMapping,
                          ValidaIDs validador
    ) {
        this.aluguelRepository = aluguelRepository;
        this.locatarioRepository = locatarioRepository;
        this.livroRepository = livroRepository;
        this.aluguelMapping = aluguelMapping;
        this.validador = validador;
    }

    public List<Aluguel> getAll() {
        return aluguelRepository.findAll();
    }

    public List<Livro> getAllLivrosLocatarioById(Long id) {
        locatarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Locatário não localizado!"));

        return livroRepository.findAllByLocatarioRental(id);
    }

    @Transactional
    public AluguelCreatedResponse save(AluguelRequest aluguel) {
        DadosValidados dados = checkIn(aluguel
                .getLocatarioID(), aluguel.getLivrosID());

        Aluguel aluguelEntity = aluguelMapping.toEntity(aluguel);
        aluguelEntity.setLocatario(dados.getLocatario());
        aluguelEntity.setId(null);

        aluguelEntity.setLivros(new HashSet<>(dados.getLivros()));

        Aluguel aluguelSalvo = aluguelRepository
                .save(aluguelEntity);

        return aluguelMapping.toCreatedResponse(aluguelSalvo);
    }

    @Transactional
    public AluguelCreatedResponse update(AluguelRequest aluguelAtualizado, Aluguel aluguelAtual) {
        DadosValidados dados = checkIn(aluguelAtualizado.getLocatarioID(),
                aluguelAtualizado.getLivrosID());

        BeanUtils.copyProperties(aluguelAtualizado, aluguelAtual, "id");

        aluguelAtual.setLivros(new HashSet<>(dados.getLivros()));
        Aluguel aluguelSalvo = aluguelRepository.save(aluguelAtual);

        return aluguelMapping.toCreatedResponse(aluguelSalvo);
    }

    public void deleteOneById(Long id) {
        aluguelRepository.deleteById(id);
    }

    private DadosValidados checkIn(Long locatarioID, List<Long> livrosRequestIDs) {
        Locatario locatario = locatarioRepository.findById(locatarioID)
                .orElseThrow(() -> new EntityNotFound("Locatário não localizado!"));

        List<Livro> livros = livroRepository.findAllById(livrosRequestIDs);

        Iterator<Long> livrosIDs = livros.stream().map(Livro::getId).iterator();

        List<Long> idsInvalidos = validador.execute(livrosRequestIDs, livrosIDs);

        if(idsInvalidos != null) {
            throw new EntityNotFound("Livros não localizados!: ID's " + idsInvalidos);
        }

        return new DadosValidados(locatario, livros);
    }

    private static class DadosValidados {
        private Locatario locatario;
        private List<Livro> livros;

        public DadosValidados(Locatario locatario,
                              List<Livro> livros
        ) {
            this.locatario = locatario;
            this.livros = livros;
        }

        public List<Livro> getLivros() {
            return livros;
        }

        public Locatario getLocatario() {
            return locatario;
        }
    }
}
