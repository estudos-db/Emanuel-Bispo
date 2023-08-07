package br.tec.rental.library.services;

import br.tec.rental.library.api.dto.aluguel.AluguelCreatedResponse;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.AluguelMapping;
import br.tec.rental.library.domain.model.Livro;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.LivroRepository;
import br.tec.rental.library.domain.repository.LocatarioRepository;
import br.tec.rental.library.domain.service.AluguelService;
import br.tec.rental.library.domain.service.validator.ValidaIDs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.tec.rental.library.constants.AluguelConstants.*;
import static br.tec.rental.library.constants.LivroConstants.LIVRO_1;
import static br.tec.rental.library.constants.LocatarioConstants.LOCATARIO_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AluguelServiceTest {

    private AluguelService aluguelService;
    @Mock
    private AluguelRepository aluguelRepository;
    @Mock
    private LocatarioRepository locatarioRepository;
    @Mock
    private LivroRepository livroRepository;
    @Mock
    private AluguelMapping aluguelMapping;

    @BeforeEach
    void setup () {
        aluguelService = new AluguelService(aluguelRepository,
                locatarioRepository, livroRepository,
                aluguelMapping, new ValidaIDs()
        );
    }

    @Test
    void deveBuscarPorLocatarioIdERetornarLivro() {
        when(locatarioRepository.findById(LOCATARIO_1.getId()))
                .thenReturn(Optional.of(LOCATARIO_1));

        when(livroRepository.findAllByLocatarioRental(LOCATARIO_1.getId()))
                .thenReturn(List.of(LIVRO_1));

        List<Livro> livros = aluguelService
                .getAllLivrosLocatarioById(LOCATARIO_1.getId());

        assertThat(livros).hasSize(1).contains(LIVRO_1);
    }

    @Test
    void deveBuscarPorLocatarioIdInvalidoRetornaExcecao() {
        Long idInvalido = 2l;

        when(locatarioRepository.findById(idInvalido))
                .thenThrow(ResourceNotFoundException.class);

        assertThatThrownBy(() -> aluguelService
                .getAllLivrosLocatarioById(idInvalido))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void deveSalvarAluguelComSucesso() {
        when(locatarioRepository.findById(LOCATARIO_1.getId()))
                .thenReturn(Optional.of(LOCATARIO_1));

        when(livroRepository.findAllById(ALUGUEL_REQUEST_1.getLivrosID()))
                .thenReturn(List.of(LIVRO_1));

        when(aluguelMapping.toEntity(ALUGUEL_REQUEST_1)).thenReturn(ALUGUEL_1);

        when(aluguelRepository.save(ALUGUEL_1)).thenReturn(ALUGUEL_1);

        when(aluguelMapping.toCreatedResponse(ALUGUEL_1)).thenReturn(ALUGUEL_CREATED_1);

        AluguelCreatedResponse save = aluguelService.save(ALUGUEL_REQUEST_1);

        assertThat(save).isEqualTo(ALUGUEL_CREATED_1);
    }

    @Test
    void deveAtualizarAluguelComSucesso() {
        when(locatarioRepository.findById(LOCATARIO_1.getId()))
                .thenReturn(Optional.of(LOCATARIO_1));

        when(livroRepository.findAllById(ALUGUEL_REQUEST_1.getLivrosID()))
                .thenReturn(List.of(LIVRO_1));

        when(aluguelRepository.save(ALUGUEL_1)).thenReturn(ALUGUEL_1);

        when(aluguelMapping.toCreatedResponse(ALUGUEL_1)).thenReturn(ALUGUEL_CREATED_1);

        AluguelCreatedResponse save = aluguelService.update(ALUGUEL_REQUEST_1, ALUGUEL_1);

        assertThat(save).isEqualTo(ALUGUEL_CREATED_1);
    }
}
