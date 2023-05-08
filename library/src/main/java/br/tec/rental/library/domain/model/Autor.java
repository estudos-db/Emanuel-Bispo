package br.tec.rental.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.Year;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Autor {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    @Column(unique = true)
    private String cpf;
    private Year ano_nascimento;
    @Enumerated(EnumType.STRING)
    private Genero sexo;
}
