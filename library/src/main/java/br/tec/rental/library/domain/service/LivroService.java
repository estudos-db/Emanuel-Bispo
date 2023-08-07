package br.tec.rental.library.domain.service;

import br.tec.rental.library.api.dto.library.OpenLibraryResponse;
import br.tec.rental.library.api.dto.livro.LivroRequest;
import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.api.exception.EntityNotFound;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.LivroMapping;
import br.tec.rental.library.client.openlibrary.OpenLibraryClient;
import br.tec.rental.library.domain.model.Aluguel;
import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.model.Livro;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.AutorRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.service.utils.FormatadorData;
import br.tec.rental.library.domain.service.validator.ValidaIDs;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

@Service
public class LivroService {

    private final LivroRepository livroRepository;
    private final AutorRepository autorRepository;
    private final AluguelRepository aluguelRepository;
    private final LivroMapping livroMapping;
    private final ValidaIDs validaAutoresID;
    private final OpenLibraryClient openLibraryClient;

    private static final String FORMATADO_DATA_OPENLIBRARY_RESPONSE = "MMM d, yyy";
    private static final String FORMATADO_DATA_API = "dd MMMM yyyy";


    @Autowired
    public LivroService(LivroRepository livroRepository, AluguelRepository aluguelRepository,
                        AutorRepository autorRepository, LivroMapping livroMapping,
                        ValidaIDs validaAutoresID, OpenLibraryClient openLibraryClient
    ) {
        this.livroRepository = livroRepository;
        this.autorRepository = autorRepository;
        this.aluguelRepository = aluguelRepository;
        this.livroMapping = livroMapping;
        this.validaAutoresID = validaAutoresID;
        this.openLibraryClient = openLibraryClient;
    }

    public List<Livro> getAll() {
        return livroRepository.findAll();
    }

    public Livro getOneById (Long id) {
        return livroRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Livro não localizado!"));
    }

    public List<Livro> getAllByAutorName(String nome) {
        return livroRepository.findAllByAutorNome(nome);
    }

    public List<Livro> getAllAvailableToRent() {
        return livroRepository.findAllAvailableToRent();
    }

    public List<Livro> getAllUnavailableToRent() {
        return livroRepository.findAllUnavailableToRent();
    }

    @Transactional
    public Livro save(LivroRequest novoLivro) {
        OpenLibraryResponse openLibraryResponse = openLibraryClient.buscaLivroPorIsbn(novoLivro.getIsbn());

        Date dataPublicacaoFormatada = FormatadorData
                .execute(FORMATADO_DATA_OPENLIBRARY_RESPONSE, FORMATADO_DATA_API, openLibraryResponse.getData_publicacao());

        openLibraryResponse.setData_publicacao(null);

        Livro livro = livroMapping.toEntity(openLibraryResponse);
        livro.setData_publicacao(dataPublicacaoFormatada);

        return livroRepository.save(livro);
    }

    @Transactional
    public Livro update(LivroRequest livroAtualizado, Livro livroAtual) {

        List<Autor> autors = checkIn(livroAtualizado.getAutor());

        BeanUtils.copyProperties(livroAtualizado, livroAtual, "id");
        livroAtual.setAutor(new HashSet<>(autors));

        return livroRepository
                .save(livroAtual);
    }

    public void deleteOneById(Long id) {
        List<Aluguel> livros = aluguelRepository.findByLivrosId(id);

        if(!livros.isEmpty()) {
            throw new ConflictException("O livro possui aluguel registrado!");
        }

        livroRepository.deleteById(id);
    }

    private List<Autor> checkIn(List<Long> autoresRequestID) {
        List<Autor> autores = autorRepository.findAllById(autoresRequestID);

        Iterator<Long> idsAutores = autores.stream()
                .map(Autor::getId)
                .iterator();

        List<Long> idsInvalidos = validaAutoresID.execute(autoresRequestID, idsAutores);

        if(idsInvalidos != null) {
            throw new EntityNotFound("Autor(es) não existe(m) no sistema!: ID'(s) " + idsInvalidos);
        }

        return autores;
    }

}
