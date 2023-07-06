package br.tec.rental.library.api.controller;

import br.tec.rental.library.api.dto.locatario.LocatarioCreatedResponse;
import br.tec.rental.library.api.dto.locatario.LocatarioRequest;
import br.tec.rental.library.api.dto.locatario.LocatarioResponse;
import br.tec.rental.library.api.exception.ResourceNotFoundException;
import br.tec.rental.library.api.mapping.LocatarioMapping;
import br.tec.rental.library.domain.model.Locatario;
import br.tec.rental.library.domain.service.LocatarioService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/locatarios")
public class LocatarioController {

    @Autowired
    private LocatarioService locatarioService;
    @Autowired
    private LocatarioMapping locatarioMapping;

    @GetMapping
    public ResponseEntity<List<LocatarioResponse>> getAll() {
        return ResponseEntity.ok(locatarioService.getAll());
    }

    @PostMapping
    public ResponseEntity<LocatarioCreatedResponse> create(@Valid @RequestBody LocatarioRequest locatarioRequest,
                                                            UriComponentsBuilder uriBulder
    ) {
        Locatario locatarioResponse = locatarioService.save(locatarioMapping.toEntity(locatarioRequest));
        return ResponseEntity.created(uriBulder.path("/locatario/{id}")
                .buildAndExpand(locatarioResponse.getId()).toUri())
                .body(locatarioMapping.toCreatedResponse(locatarioResponse));
    }

    @PutMapping("/{locatarioID}")
    public ResponseEntity update(@PathVariable Long locatarioID,@Valid @RequestBody LocatarioRequest locatarioRequest) {
        Locatario locatarioAtual = locatarioService.getOneById(locatarioID)
                .orElseThrow(() -> new ResourceNotFoundException("Locatário não localizado!"));

        BeanUtils.copyProperties(locatarioRequest, locatarioAtual, "id");
        Locatario locatario = locatarioService.save(locatarioAtual);


        return ResponseEntity.ok(locatarioMapping.toResponse(locatario));
    }

    @DeleteMapping("/{locatarioID}")
    public ResponseEntity delete(@PathVariable Long locatarioID) {
        locatarioService.deleteOneById(locatarioID);
        return ResponseEntity.noContent().build();
    }
}
