package br.tec.rental.library.domain.service;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.repository.AutorRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.service.validator.ValidaCpfAutorJaExiste;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AutorService {

    private AutorRepository autorRepository;
    private LivroRepository livroRepository;
    private ValidaCpfAutorJaExiste validador;

    @Autowired
    public AutorService(AutorRepository autorRepository,
                        LivroRepository livroRepository, ValidaCpfAutorJaExiste validador
    ) {
        this.autorRepository = autorRepository;
        this.livroRepository = livroRepository;
        this.validador = validador;
    }

    public List<Autor> getAll() {
        return autorRepository.findAll();
    }

    public Optional<Autor> getOneByCpf(String cpf) {
        return autorRepository.findByCpf(cpf);
    }

    public Optional<Autor> getOneById(Long id) {
        return autorRepository.findById(id);
    }

    public Autor getOneByNome(String nome) {
        return autorRepository.findByNome(nome)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não localizado!"));
    }

    public Autor save(Autor autor) {
        validador.execute(autor.getId(), autor.getCpf());
        return autorRepository.save(autor);
    }

    public void deleteOneById(Long id) {
        int autorTemLivros = livroRepository.findAllByAutorId(id).size();
        if(autorTemLivros > 0)
            throw new ConflictException("Não é possível deletar: Autor possui livro registrado no sistema!");

        autorRepository.deleteById(id);
    }

}
