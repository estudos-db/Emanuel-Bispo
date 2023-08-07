package br.tec.rental.library.api.dto.livro;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
public class LivroResume {
    private Long id;
    private String titulo;
    private String isbn;
}
