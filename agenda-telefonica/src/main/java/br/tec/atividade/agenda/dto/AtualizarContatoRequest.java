package br.tec.atividade.agenda.dto;

import br.tec.atividade.agenda.model.Endereco;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class AtualizarContatoRequest {
    private Long id;
    private String nome;
    private String sobrenome;
    private String telefone;
    private String email;
    private Endereco endereco;
}
