package br.tec.rental.library.services;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.api.exception.EntityNotFound;
import br.tec.rental.library.api.mapping.LivroMapping;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.AutorRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.service.LivroService;
import br.tec.rental.library.domain.service.validator.ValidaIDs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static br.tec.rental.library.constants.AluguelConstants.ALUGUEL_1;
import static br.tec.rental.library.constants.AutorConstants.AUTOR_1;
import static br.tec.rental.library.constants.LivroConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LivroServiceTest {

    @InjectMocks
    private LivroService livroService;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private AutorRepository autorRepository;
    @Mock
    private AluguelRepository aluguelRepository;
    @Mock
    private LivroMapping livroMapping;

    @BeforeEach
    void setup () {
        livroService = new LivroService(livroRepository, aluguelRepository,
                autorRepository, livroMapping, new ValidaIDs()
        );
    }

    @Test
    void deveSalvarUmLivroComSucesso() {
        when(autorRepository.findAllById(LIVRO_REQUEST_1.getAutor()))
                .thenReturn(List.of(AUTOR_1));

        when(livroMapping.toEntity(LIVRO_REQUEST_1)).thenReturn(LIVRO_1);

        when(livroRepository.save(LIVRO_1)).thenReturn(LIVRO_1);

        assertThat(livroService.save(LIVRO_REQUEST_1)).isEqualTo(LIVRO_1);
    }

    @Test
    void deveRetornarOLivroAtualizadoComSucesso() {
        when(autorRepository.findAllById(LIVRO_REQUEST_1.getAutor()))
                .thenReturn(List.of(AUTOR_1));

        when(livroRepository.save(LIVRO_1)).thenReturn(LIVRO_1);

        assertThat(livroService.update(LIVRO_REQUEST_1, LIVRO_1)).isEqualTo(LIVRO_1);
    }

    @Test
    void deveLancarExcecaoParaLivroComAutorInvalido() {
        when(autorRepository.findAllById(LIVRO_REQUEST_INVALID.getAutor()))
                .thenReturn(List.of());

        assertThatThrownBy(() -> livroService.save(LIVRO_REQUEST_INVALID))
                .isInstanceOf(EntityNotFound.class);
    }

    @Test
    void deveLancarExcecaoAoTentarDeletarLivroComAluguelRegistrado() {
        when(aluguelRepository.findByLivrosId(LIVRO_1.getId()))
                .thenReturn(List.of(ALUGUEL_1));

        assertThatThrownBy(() -> livroService.deleteOneById(LIVRO_1.getId()))
                .isInstanceOf(ConflictException.class);
    }
}
