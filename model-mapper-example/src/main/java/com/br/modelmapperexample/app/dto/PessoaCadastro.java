package com.br.modelmapperexample.app.dto;

import com.br.modelmapperexample.app.model.Endereco;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PessoaCadastro {
    private String nome;
    private String cpf;
    private Integer idade;
    private String email;
    private Endereco endereco;
}
