package br.tec.rental.library.api.dto.locatario;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
public class LocatarioResume {
    private Long id;
    private String nome;
    private String cpf;
    private String telefone;
    private String email;
}
