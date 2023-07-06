package br.tec.rental.library.constants;

import br.tec.rental.library.api.dto.locatario.LocatarioResume;
import br.tec.rental.library.domain.model.Genero;
import br.tec.rental.library.domain.model.Locatario;

import java.time.LocalDate;

public class LocatarioConstants {
    public static Locatario LOCATARIO_1 = new Locatario(1L, "Axl", "123456", LocalDate.of(1962, 6, 2), Genero.MASCULINO, "mail1.com", "912345678");
    public static Locatario LOCATARIO_2 = new Locatario(2L, "Kurt", "123456", LocalDate.of(1967, 2, 20), Genero.MASCULINO, "mail1.com", "912345678");
    public static LocatarioResume LOCATARIO_1_RESUME = new LocatarioResume(1L, "Axl", "123456", "mail1.com", "912345678");
}
