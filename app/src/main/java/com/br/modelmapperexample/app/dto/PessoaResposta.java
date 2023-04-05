package com.br.modelmapperexample.app.dto;

import com.br.modelmapperexample.app.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaResposta {
    private String nome;
    private Integer idade;
    private Endereco endereco;
}
