package br.tec.atividade.agenda.service;

import br.tec.atividade.agenda.model.Agenda;
import br.tec.atividade.agenda.model.Contato;
import br.tec.atividade.agenda.repository.AgendaRepository;
import br.tec.atividade.agenda.repository.ContatoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static br.tec.atividade.agenda.constants.AgendaConstants.AGENDA;
import static br.tec.atividade.agenda.constants.AgendaConstants.AGENDA_COM_CONTATO;
import static br.tec.atividade.agenda.constants.ContatoConstants.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class AgendaContatoServiceTest {

    @InjectMocks
    private AgendaContatoService agendaContatoService;
    @Mock
    private AgendaRepository agendaRepository;
    @Mock
    private ContatoRepository contatoRepository;

    @Test
    void listaTodosContatoDeveRetornarAgendaComContatos() {
        when(agendaRepository.findAll())
                .thenReturn(List.of(AGENDA_COM_CONTATO));

        List<Agenda> agendaListSut = agendaContatoService.listaTodos();

        assertThat(agendaListSut).hasSize(1).contains(AGENDA_COM_CONTATO);
    }

    @Test
    void adicionaContatoComDadosValidosDeveRetornarContato() {
        when(agendaRepository.findById(AGENDA.getId())).thenReturn(Optional.of(AGENDA));
        when(agendaRepository.save(AGENDA)).thenReturn(AGENDA);

        Contato contatoSut = agendaContatoService.adiciona(AGENDA.getId(), CONTATO);

        assertThat(contatoSut).isEqualTo(CONTATO);
    }

    @Test
    void adicionaContatoComDadosInvalidosDeveLancarExcecao() {
        when(agendaRepository.findById(AGENDA.getId())).thenReturn(Optional.of(AGENDA));
        when(agendaRepository.save(AGENDA))
                .thenThrow(RuntimeException.class);

        assertThatThrownBy(() -> agendaContatoService.adiciona(AGENDA.getId(), INVALID_CONTATO))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void atualizaContatoComDadosValidosDeveRetornarContatoAtualizado() {
        when(agendaRepository.findById(AGENDA_COM_CONTATO.getId()))
                .thenReturn(Optional.of(AGENDA_COM_CONTATO));
        when(contatoRepository.save(CONTATO_ATUALIZADO)).thenReturn(CONTATO_ATUALIZADO);

        Contato contatoAtualizadoSut = agendaContatoService
                .atualiza(AGENDA_COM_CONTATO.getId(), CONTATO_ATUALIZADO);

        assertThat(contatoAtualizadoSut).isEqualTo(CONTATO_ATUALIZADO);
    }

    @Test
    void atualizaContatoComDadosInvalidosDeveLancarExcecao() {
        when(agendaRepository.findById(AGENDA_COM_CONTATO.getId()))
                .thenReturn(Optional.of(AGENDA_COM_CONTATO));

        assertThatThrownBy(() -> agendaContatoService.atualiza(AGENDA_COM_CONTATO.getId(), INVALID_CONTATO))
                .isInstanceOf(RuntimeException.class);
    }

    @Test
    void atualizaContatoComContatoAusenteNaAgendaDeveLancarExcecao() {
        when(agendaRepository.findById(AGENDA.getId()))
                .thenReturn(Optional.of(AGENDA));

        assertThatThrownBy(() -> agendaContatoService.atualiza(AGENDA.getId(), CONTATO))
                .isInstanceOf(RuntimeException.class);
    }

}
