package br.tec.rental.library.constants;

import br.tec.rental.library.api.dto.livro.LivroRequest;
import br.tec.rental.library.api.dto.livro.LivroResume;
import br.tec.rental.library.domain.model.Livro;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static br.tec.rental.library.constants.AutorConstants.AUTOR_1;

public class LivroConstants {
    public static LivroRequest LIVRO_REQUEST_1 = new LivroRequest("Clean code", "2345678", LocalDate.of(2008, 8, 1), List.of(AUTOR_1.getId()));
    public static LivroRequest LIVRO_REQUEST_INVALID = new LivroRequest("Clean code", "2345678", LocalDate.of(2008, 8, 1), List.of(0L));
    public static Livro LIVRO_1 = new Livro(1L, "Clean code", "2345678", LocalDate.of(2008, 8, 1), Set.of(AUTOR_1));
    public static LivroResume LIVRO_1_RESUME = new LivroResume(1L, "Clean code", "2345678");
}
