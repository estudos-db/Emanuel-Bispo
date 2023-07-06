package br.tec.rental.library.api.dto.autor;

import br.tec.rental.library.domain.model.Genero;
import lombok.Getter;
import lombok.Setter;

import java.time.Year;

@Getter @Setter
public class AutorResponse {
    private Long id;
    private String nome;
    private String cpf;
    private Year ano_nascimento;
    private Genero sexo;
}
