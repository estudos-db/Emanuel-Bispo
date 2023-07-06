package br.tec.rental.library.services;

import br.tec.rental.library.api.exception.ConflictException;
import br.tec.rental.library.api.mapping.LocatarioMapping;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.repository.LocatarioRepository;
import br.tec.rental.library.domain.service.LocatarioService;
import br.tec.rental.library.domain.service.validator.ValidaDadosLocatarioJaExiste;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.tec.rental.library.constants.AluguelConstants.ALUGUEL_1;
import static br.tec.rental.library.constants.LocatarioConstants.LOCATARIO_1;
import static br.tec.rental.library.constants.LocatarioConstants.LOCATARIO_2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocatarioServiceTest {

    @InjectMocks
    private LocatarioService locatarioService;
    @Mock
    private LocatarioRepository locatarioRepository;
    @Mock
    private LocatarioMapping locatarioMapping;
    @Mock
    private AluguelRepository aluguelRepository;
    private ValidaDadosLocatarioJaExiste validaDadosLocatarioJaExiste;

    @BeforeEach
    void setup() {
        locatarioService = new LocatarioService(locatarioRepository, locatarioMapping,
                new ValidaDadosLocatarioJaExiste(locatarioRepository), aluguelRepository
        );
    }

    @Test
    void deveRetornarOLocatarioSalvoComSucesso() {
        when(locatarioRepository.findByCpf(LOCATARIO_1.getCpf())).thenReturn(Optional.empty());
        when(locatarioRepository.findByEmail(LOCATARIO_1.getEmail())).thenReturn(Optional.empty());

        when(locatarioRepository.save(LOCATARIO_1)).thenReturn(LOCATARIO_1);

        assertThat(locatarioService.save(LOCATARIO_1)).isEqualTo(LOCATARIO_1);
    }

    @Test
    void deveLancarExcecaoAoTentarCadastrarLocatarioComEmailJaEmUso() {
        when(locatarioRepository.findByEmail(LOCATARIO_1.getEmail())).thenReturn(Optional.of(LOCATARIO_2));
        assertThatThrownBy(() -> locatarioService.save(LOCATARIO_1)).isInstanceOf(ConflictException.class);
    }

    @Test
    void deveLancarExcecaoAoTentarCadastrarLocatarioComCpfJaEmUso() {
        when(locatarioRepository.findByCpf(LOCATARIO_1.getCpf())).thenReturn(Optional.of(LOCATARIO_2));
        assertThatThrownBy(() -> locatarioService.save(LOCATARIO_1)).isInstanceOf(ConflictException.class);
    }

    @Test
    void deveLancarExcecaoAoTentarDeletarLocatarioComDevolucaoPendente() {
        when(aluguelRepository.findByLocatarioId(LOCATARIO_1.getId())).thenReturn(List.of(ALUGUEL_1));
        assertThatThrownBy(() -> locatarioService.deleteOneById(LOCATARIO_1.getId()))
                .isInstanceOf(ConflictException.class);
    }
}
