package br.tec.rental.library.constants;

import br.tec.rental.library.domain.model.Autor;
import br.tec.rental.library.domain.model.Genero;

import java.time.Year;

public class AutorConstants {
    public static Autor AUTOR_1 = new Autor(1L, "Robert C.", "1234567", Year.of(1950), Genero.MASCULINO);
    public static Autor AUTOR_2 = new Autor(2L, "William Shakespeare", "1234567", Year.of(1560), Genero.MASCULINO);
}
