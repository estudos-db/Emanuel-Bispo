package br.tec.rental.library.api.dto.livro;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class LivroRequest {
    @NotBlank(message = "{notblank}")
    private String isbn;
    @JsonProperty(value = "autoresID")
    private List<Long> autor;
}
