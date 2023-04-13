package com.br.modelmapperexample.app.model;

import lombok.*;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Pessoa {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String cpf;
    private Integer idade;
    private String email;
    @OneToOne(cascade = CascadeType.ALL)
    private Endereco endereco;
}
