package br.tec.rental.library.api.mapping;

import br.tec.rental.library.api.dto.livro.LivroRequest;
import br.tec.rental.library.api.dto.livro.LivroResponse;
import br.tec.rental.library.domain.model.Livro;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class LivroMapping {

    @Autowired
    private ModelMapper modelMapper;

    public Livro toEntity(LivroRequest livroRequest) {
        return modelMapper.map(livroRequest, Livro.class);
    }

    public LivroResponse toResponseModel(Livro livro) {
        return modelMapper.map(livro, LivroResponse.class);
    }

    public List<LivroResponse> toResponseModelList(List<Livro> livros) {
        return livros.stream().map(livro ->
            modelMapper.map(livro, LivroResponse.class))
                .collect(Collectors.toList());
    }

}
