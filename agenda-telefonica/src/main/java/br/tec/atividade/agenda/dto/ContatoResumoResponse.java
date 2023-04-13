package br.tec.atividade.agenda.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ContatoResumoResponse {
    private Long id;
    private String nome;
    private String telefone;
    private EnderecoResumoResponse endereco;
}
