package br.tec.rental.library.client.openlibrary;

import br.tec.rental.library.api.dto.library.OpenLibraryResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "openLibrary", url = "https://openlibrary.org/")
public interface OpenLibraryClient {
    @GetMapping(value = "/isbn/{isbn}.json")
    OpenLibraryResponse buscaLivroPorIsbn(@PathVariable String isbn);
}
