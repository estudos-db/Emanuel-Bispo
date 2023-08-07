package br.tec.rental.library.api.controller;

import br.tec.rental.library.api.dto.livro.LivroRequest;
import br.tec.rental.library.api.dto.livro.LivroResponse;
import br.tec.rental.library.api.mapping.LivroMapping;
import br.tec.rental.library.domain.model.Livro;
import br.tec.rental.library.domain.service.LivroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/livros")
public class LivroController {

    @Autowired
    private LivroService livroService;
    @Autowired
    private LivroMapping livroMapping;

    @GetMapping
    public ResponseEntity<List<LivroResponse>> getAll() {
        return ResponseEntity.ok(livroMapping.toResponseModelList(livroService.getAll()));
    }

    @GetMapping("/filter")
    public ResponseEntity<List<LivroResponse>> getByAutorNameFilter(@RequestParam(name = "autor") String autor) {
        List<Livro> livros = livroService.getAllByAutorName(autor);
        return ResponseEntity.ok(livroMapping.toResponseModelList(livros));
    }

    @GetMapping("/{livroID}")
    public ResponseEntity<LivroResponse> getById(@PathVariable Long livroID) {
        Livro livro = livroService.getOneById(livroID);
        return ResponseEntity.ok(livroMapping.toResponseModel(livro));
    }

    @GetMapping("/aluguel/disponivel")
    public ResponseEntity<List<Livro>> getAllAvailableToRent() {
        return ResponseEntity.ok(livroService.getAllAvailableToRent());
    }

    @GetMapping("/aluguel/indisponivel")
    public ResponseEntity<List<Livro>> getAllUnavailableToRent() {
        return ResponseEntity.ok(livroService.getAllUnavailableToRent());
    }

    @PostMapping
    public ResponseEntity<LivroResponse> create(@Valid @RequestBody LivroRequest livro,
             UriComponentsBuilder uriBuilder
    ) {
        Livro livroMap = livroService.save(livro);
        return ResponseEntity.created(uriBuilder.path("/livro/{id}")
                .buildAndExpand(livroMap.getId()).toUri())
                .body(livroMapping.toResponseModel(livroMap));
    }

    @PutMapping("/{livroID}")
    public ResponseEntity update(@PathVariable Long livroID, @Valid @RequestBody LivroRequest livroAtualizado) {
        Livro livroAtual = livroService.getOneById(livroID);

        livroService.update(livroAtualizado ,livroAtual);

        return ResponseEntity.ok(livroMapping.toResponseModel(livroAtual));
    }

    @DeleteMapping("/{livroID}")
    public ResponseEntity delete(@PathVariable Long livroID) {
        livroService.deleteOneById(livroID);
        return ResponseEntity.noContent().build();
    }

}
