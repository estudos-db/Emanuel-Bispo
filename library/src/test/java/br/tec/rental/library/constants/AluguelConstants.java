package br.tec.rental.library.constants;

import br.tec.rental.library.api.dto.aluguel.AluguelCreatedResponse;
import br.tec.rental.library.api.dto.aluguel.AluguelRequest;
import br.tec.rental.library.domain.model.Aluguel;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import static br.tec.rental.library.constants.LivroConstants.LIVRO_1;
import static br.tec.rental.library.constants.LivroConstants.LIVRO_1_RESUME;
import static br.tec.rental.library.constants.LocatarioConstants.LOCATARIO_1;
import static br.tec.rental.library.constants.LocatarioConstants.LOCATARIO_1_RESUME;

public class AluguelConstants {
    public static Aluguel ALUGUEL_1 = new Aluguel(1L, LOCATARIO_1, Set.of(LIVRO_1), LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), null);
    public static AluguelRequest ALUGUEL_REQUEST_1 = new AluguelRequest(1L, List.of(1L), LocalDate.of(2023, 4, 10));
    public static AluguelCreatedResponse ALUGUEL_CREATED_1 = new AluguelCreatedResponse(1L, LOCATARIO_1_RESUME, List.of(LIVRO_1_RESUME), LocalDate.of(2023, 1, 1), LocalDate.of(2023, 1, 2), null);
}
