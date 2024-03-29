package br.tec.rental.library.api.dto.livro;

import br.tec.rental.library.api.dto.autor.AutorResume;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class LivroResponse {
    private Long id;
    private String titulo;
    private String isbn;
    private Date data_publicacao;
    @JsonProperty(value = "autores")
    private List<AutorResume> autor;
}
