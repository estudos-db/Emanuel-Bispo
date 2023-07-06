package br.tec.rental.library.api.dto.locatario;

import br.tec.rental.library.domain.model.Genero;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter @Getter
public class LocatarioCreatedResponse {
    private Long id;
    private String nome;
    private String cpf;
    private LocalDate data_nascimento;
    private Genero sexo;
    private String email;
    private String telefone;
}
