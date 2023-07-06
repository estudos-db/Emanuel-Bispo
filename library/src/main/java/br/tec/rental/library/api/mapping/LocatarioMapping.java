package br.tec.rental.library.api.mapping;

import br.tec.rental.library.api.dto.locatario.LocatarioCreatedResponse;
import br.tec.rental.library.api.dto.locatario.LocatarioRequest;
import br.tec.rental.library.api.dto.locatario.LocatarioResponse;
import br.tec.rental.library.domain.model.Locatario;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LocatarioMapping {
    @Autowired
    private ModelMapper modelMapper;

    public Locatario toEntity(LocatarioRequest locatarioRequest) {
        return modelMapper.map(locatarioRequest, Locatario.class);
    }

    public LocatarioResponse toResponse(Locatario locatario) {
        return modelMapper.map(locatario, LocatarioResponse.class);
    }

    public LocatarioCreatedResponse toCreatedResponse(Locatario locatario) {
        return modelMapper.map(locatario, LocatarioCreatedResponse.class);
    }

    public List<LocatarioResponse> toResponseList(List<Locatario> locatarios) {
        return locatarios.stream().map(locatario -> modelMapper.map(locatario, LocatarioResponse.class))
                .collect(Collectors.toList());
    }
}
