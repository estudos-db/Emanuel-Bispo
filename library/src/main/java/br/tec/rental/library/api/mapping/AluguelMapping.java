package br.tec.rental.library.api.mapping;

import br.tec.rental.library.api.dto.aluguel.AluguelCreatedResponse;
import br.tec.rental.library.api.dto.aluguel.AluguelRequest;
import br.tec.rental.library.api.dto.aluguel.AluguelResponse;
import br.tec.rental.library.domain.model.Aluguel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class AluguelMapping {
    private ModelMapper modelMapper;

    @Autowired
    public AluguelMapping(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    public Aluguel toEntity(AluguelRequest aluguelRequest) {
        return modelMapper.map(aluguelRequest, Aluguel.class);
    }

    public AluguelCreatedResponse toCreatedResponse(Aluguel aluguel) {
        return modelMapper.map(aluguel, AluguelCreatedResponse.class);
    }

    public AluguelResponse toResponseModel(Aluguel aluguel) {
        return modelMapper.map(aluguel, AluguelResponse.class);
    }

    public List<AluguelResponse> toResponseList(List<Aluguel> aluguelList) {
        return aluguelList.stream().map(aluguel -> modelMapper.map(aluguel, AluguelResponse.class))
                .collect(Collectors.toList());
    }
}
