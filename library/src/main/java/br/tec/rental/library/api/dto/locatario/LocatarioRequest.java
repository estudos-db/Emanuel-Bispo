package br.tec.rental.library.api.dto.locatario;

import br.tec.rental.library.domain.model.Genero;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter @Setter
public class LocatarioRequest {
    @NotBlank(message = "{notblank}")
    private String nome;
    @NotBlank(message = "{notblank}")
    private String cpf;
    @NotNull(message = "{notnull}")
    private LocalDate data_nascimento;
    private Genero sexo;
    @Email
    @NotNull(message = "{notnull}")
    private String email;
    @NotNull(message = "{notnull}")
    @Size(min = 11, max = 11, message = "Deve conter 11 digitos")
    private String telefone;
}
