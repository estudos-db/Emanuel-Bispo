package br.tec.rental.library.api.dto.library;

import com.fasterxml.jackson.annotation.JsonAlias;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenLibraryResponse {
    @JsonAlias(value = "title")
    private String titulo;

    @JsonAlias(value = "isbn_10")
    private List<String> isbn;

    @JsonAlias(value = "publish_date")
    private String data_publicacao;
}
