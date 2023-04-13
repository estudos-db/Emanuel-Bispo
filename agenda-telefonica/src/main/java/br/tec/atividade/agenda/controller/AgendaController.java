package br.tec.atividade.agenda.controller;

import br.tec.atividade.agenda.dto.AgendaResponse;
import br.tec.atividade.agenda.dto.AtualizarContatoRequest;
import br.tec.atividade.agenda.model.Agenda;
import br.tec.atividade.agenda.model.Contato;
import br.tec.atividade.agenda.repository.AgendaRepository;
import br.tec.atividade.agenda.service.AgendaContatoService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/agenda")
@RequiredArgsConstructor
public class AgendaController {

    private final AgendaRepository agendaRepository;
    private final AgendaContatoService agendaContatoService;
    private final ModelMapper modelMapper;

    @GetMapping
    public ResponseEntity<List<AgendaResponse>> getAll() {
        List<AgendaResponse> pessoasMapeadas = agendaContatoService.listaTodos()
                .stream()
                .map(e -> modelMapper.map(e, AgendaResponse.class))
                .collect(Collectors.toList());

        return ResponseEntity.ok(pessoasMapeadas);
    }

    @PostMapping("/nova")
    public ResponseEntity<Agenda> criaNova(@RequestBody Agenda novaAgenda, UriComponentsBuilder uriBuilder) {
        Agenda agendaCriada = agendaRepository.save(novaAgenda);

        return ResponseEntity.created(uriBuilder.path("/agenda/{id}")
                        .buildAndExpand(agendaCriada.getId()).toUri())
                .body(agendaRepository.save(novaAgenda));
    }

    @PutMapping("/{agendaID}/atualizar-contato")
    @Transactional
    public ResponseEntity<Contato> atualizarContato(@PathVariable Long agendaID,
                                                    @RequestBody AtualizarContatoRequest contatoAtualizado
    ) {

        Contato contatoMap = modelMapper.map(contatoAtualizado, Contato.class);
        return ResponseEntity.ok(agendaContatoService.atualiza(agendaID, contatoMap));

    }

    @PutMapping("/{agendaID}/adicionar-contato")
    public ResponseEntity<Contato> adicionarContato(@PathVariable Long agendaID,
                                                    @RequestBody Contato novoContato
    ) {

        Contato contato = agendaContatoService.adiciona(agendaID, novoContato);
        return ResponseEntity.ok(contato);

    }

    @DeleteMapping("/contato/{contatoID}")
    public ResponseEntity deletaContato(@PathVariable Long contatoID) {
        agendaContatoService.deleta(contatoID);
        return ResponseEntity.noContent().build();
    }

}
