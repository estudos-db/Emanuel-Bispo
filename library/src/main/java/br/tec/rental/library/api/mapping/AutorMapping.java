package br.tec.rental.library.api.mapping;

import br.tec.rental.library.api.dto.autor.AutorCreatedResponse;
import br.tec.rental.library.api.dto.autor.AutorRequest;
import br.tec.rental.library.api.dto.autor.AutorResponse;
import br.tec.rental.library.domain.model.Autor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AutorMapping {
    @Autowired
    private ModelMapper modelMapper;

    public Autor toEntity(AutorRequest autorRequest) {
        return modelMapper.map(autorRequest, Autor.class);
    }

    public AutorResponse toResponse(Autor autor) {
        return modelMapper.map(autor, AutorResponse.class);
    }

    public AutorCreatedResponse toAutorCreatedResponse(Autor autor) {
        return modelMapper.map(autor, AutorCreatedResponse.class);
    }

    public List<AutorResponse> toResponseList(List<Autor> autores) {
        return autores.stream().map(autor -> modelMapper.map(autor, AutorResponse.class))
                .collect(Collectors.toList());
    }
}
