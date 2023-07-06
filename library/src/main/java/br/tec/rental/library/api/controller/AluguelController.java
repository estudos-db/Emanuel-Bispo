package br.tec.rental.library.api.controller;

import br.tec.rental.library.api.dto.aluguel.AluguelCreatedResponse;
import br.tec.rental.library.api.dto.aluguel.AluguelRequest;
import br.tec.rental.library.api.dto.aluguel.AluguelResponse;
import br.tec.rental.library.api.dto.livro.LivroResponse;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.AluguelMapping;
import br.tec.rental.library.api.mapping.LivroMapping;
import br.tec.rental.library.domain.model.Aluguel;
import br.tec.rental.library.domain.model.Livro;
import br.tec.rental.library.domain.repository.AluguelRepository;
import br.tec.rental.library.domain.service.AluguelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/alugueis")
public class AluguelController {

    @Autowired
    private AluguelRepository aluguelRepository;
    @Autowired
    private AluguelMapping aluguelMapping;
    @Autowired
    private AluguelService aluguelService;
    @Autowired
    private LivroMapping livroMapping;

    @GetMapping
    public ResponseEntity<List<AluguelResponse>> getAll() {
        return ResponseEntity.ok(aluguelMapping.toResponseList(aluguelService.getAll()));
    }

    @GetMapping("/locatario/{id}/livros")
    public ResponseEntity<List<LivroResponse>> getAllLivrosLocatario(@PathVariable Long id) {
        List<Livro> livros = aluguelService.getAllLivrosLocatarioById(id);
        return ResponseEntity.ok(livroMapping.toResponseModelList(livros));
    }

    @PostMapping
    public ResponseEntity<AluguelCreatedResponse> create(@Valid @RequestBody AluguelRequest aluguel,
                                              UriComponentsBuilder uriBuilder
    ) {
        AluguelCreatedResponse aluguelSalvo = aluguelService.save(aluguel);
        return ResponseEntity.created(uriBuilder.path("/aluguel/{id}")
                .buildAndExpand(aluguelSalvo.getId()).toUri())
                .body(aluguelSalvo);
    }

    @PutMapping("/{aluguelID}")
    public ResponseEntity<AluguelCreatedResponse> update(@Valid @PathVariable Long aluguelID,
                                               @RequestBody AluguelRequest aluguelAtualizado
    ) {
        Aluguel aluguelAtual = aluguelRepository.findById(aluguelID)
                .orElseThrow(() -> new ResourceNotFoundException("Aluguel não localizado!"));

        AluguelCreatedResponse aluguelAtualizadoResponse = aluguelService
                .update(aluguelAtualizado, aluguelAtual);

        return ResponseEntity.ok(aluguelAtualizadoResponse);
    }

    @DeleteMapping("/{aluguelID}")
    public ResponseEntity delete(@PathVariable Long aluguelID) {
        aluguelService.deleteOneById(aluguelID);
        return ResponseEntity.noContent().build();
    }
}
