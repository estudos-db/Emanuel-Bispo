package br.tec.rental.library.services;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.repository.AutorRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.service.AutorService;
import br.tec.rental.library.domain.service.validator.ValidaCpfAutorJaExiste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.tec.rental.library.constants.AutorConstants.AUTOR_1;
import static br.tec.rental.library.constants.AutorConstants.AUTOR_2;
import static br.tec.rental.library.constants.LivroConstants.LIVRO_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AutorServiceTest {

    @InjectMocks
    private AutorService autorService;
    @Mock
    private AutorRepository autorRepository;
    @Mock
    private LivroRepository livroRepository;

    @BeforeEach
    void setup() {
        autorService = new AutorService(autorRepository, livroRepository,
                new ValidaCpfAutorJaExiste(autorRepository)
        );
    }

    @Test
    void deveSalvarUmAutorComSucesso() {
        when(autorRepository.findByCpf(AUTOR_1.getCpf())).thenReturn(Optional.empty());

        when(autorRepository.save(AUTOR_1)).thenReturn(AUTOR_1);

        Autor save = autorService.save(AUTOR_1);

        assertThat(save).isEqualTo(AUTOR_1);
    }

    @Test
    void deveLancarExcecaoParaAutorIdInvalido() {
        when(autorRepository.findByCpf(AUTOR_1.getCpf())).thenReturn(Optional.of(AUTOR_2));
        assertThatThrownBy(() -> autorService.save(AUTOR_1)).isInstanceOf(ConflictException.class);
    }

    @Test
    void deveLancarExcecaoAoTentarDeletarAutorComLivrosRegistrados() {
        when(livroRepository.findAllByAutorId(AUTOR_1.getId())).thenReturn(List.of(LIVRO_1));
        assertThatThrownBy(() -> autorService.deleteOneById(AUTOR_1.getId())).isInstanceOf(ConflictException.class);
    }
}
