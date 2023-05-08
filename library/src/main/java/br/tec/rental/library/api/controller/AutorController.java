package br.tec.rental.library.api.controller;

import br.tec.rental.library.api.dto.autor.AutorCreatedResponse;
import br.tec.rental.library.api.dto.autor.AutorRequest;
import br.tec.rental.library.api.dto.autor.AutorResponse;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.AutorMapping;
import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.service.AutorService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/autores")
public class AutorController {

    @Autowired
    private AutorService autorService;
    @Autowired
    private AutorMapping autorMapping;

    @GetMapping
    public ResponseEntity<List<AutorResponse>> getAll() {
        return ResponseEntity.ok(autorMapping.toResponseList(autorService.getAll()));
    }

    @GetMapping("/name/{nome}")
    public ResponseEntity<AutorResponse> getByName(@PathVariable String nome) {
        Autor autor = autorService.getOneByNome(nome);
        return ResponseEntity.ok(autorMapping.toResponse(autor));
    }

    @PostMapping
    public ResponseEntity<AutorCreatedResponse> create(@Valid @RequestBody AutorRequest novoAutor,
                                                       UriComponentsBuilder uriBuilder
    ) {
        Autor autorCriado = autorService.save(autorMapping.toEntity(novoAutor));
        return ResponseEntity.created(uriBuilder.path("/autor/{id}")
                        .buildAndExpand(autorCriado.getId()).toUri())
                .body(autorMapping.toAutorCreatedResponse(autorCriado));
    }

    @PutMapping("/{autorID}")
    public ResponseEntity<AutorResponse> update(@PathVariable Long autorID,
                                    @Valid @RequestBody AutorRequest autorAtualizado
    ) {
        Autor autorAtual = autorService.getOneById(autorID)
                .orElseThrow(() -> new ResourceNotFoundException("Autor não encontrado!"));

        BeanUtils.copyProperties(autorAtualizado, autorAtual, "id");
        Autor autorSalvo = autorService.save(autorAtual);

        return ResponseEntity.ok(autorMapping.toResponse(autorSalvo));
    }

    @DeleteMapping("/{autorID}")
    public ResponseEntity delete(@PathVariable Long autorID) {
        autorService.deleteOneById(autorID);
        return ResponseEntity.noContent().build();
    }
}
