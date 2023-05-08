package br.tec.rental.library.api.dto.livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LivroRequest {
    @NotBlank(message = "{notblank}")
    private String titulo;
    @NotBlank(message = "{notblank}")
    private String isbn;
    @NotNull(message = "{notnull}")
    private LocalDate data_publicacao;
    @NotEmpty(message = "{arrayid.notempty}")
    @JsonProperty(value = "autoresID")
    private List<Long> autor;
}
