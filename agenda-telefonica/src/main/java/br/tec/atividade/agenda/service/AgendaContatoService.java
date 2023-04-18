package br.tec.atividade.agenda.service;

import br.tec.atividade.agenda.model.Agenda;
import br.tec.atividade.agenda.model.Contato;
import br.tec.atividade.agenda.repository.AgendaRepository;
import br.tec.atividade.agenda.repository.ContatoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AgendaContatoService {

    private final ContatoRepository contatoRepository;
    private final AgendaRepository agendaRepository;

    public List<Agenda> listaTodos() {
        return agendaRepository.findAll();
    }

    public void deleta(Long contatoId) {
        contatoRepository.deleteById(contatoId);
    }

    public Contato atualiza(Long agendaId, Contato contatoAtualizado) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda inválida"));


        Optional<Contato> contanto = agenda.getContato()
                .stream()
                .filter(contato -> contato.getId().equals(contatoAtualizado.getId()))
                .findFirst();

        if(contanto.isEmpty()) throw new RuntimeException("Contato não faz parte desta agenda!");

        return contatoRepository.save(contatoAtualizado);
    }

    @Transactional
    public Contato adiciona(Long agendaId, Contato novoContato) {
        Agenda agenda = agendaRepository.findById(agendaId)
                .orElseThrow(() -> new RuntimeException("Agenda inválida"));

        agenda.getContato().add(novoContato);

        Integer contatoSalvoIndex = agendaRepository.save(agenda).getContato().size() -1;

        Contato contatoSalvo = agenda.getContato().get(contatoSalvoIndex);

        return contatoSalvo;
    }

}
