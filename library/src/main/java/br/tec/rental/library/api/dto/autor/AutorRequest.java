package br.tec.rental.library.api.dto.autor;

import br.tec.rental.library.domain.model.Genero;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.Year;

@Getter @Setter
public class AutorRequest {
    @NotBlank(message = "{notblank}")
    private String nome;
    @NotBlank(message = "{notblank}")
    private String cpf;
    @NotNull(message = "{notnull}")
    private Year ano_nascimento;
    private Genero sexo;
}
