package br.tec.atividade.agenda.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class AgendaResponse {
    private Long id;
    private List<ContatoResumoResponse> contato;
}
