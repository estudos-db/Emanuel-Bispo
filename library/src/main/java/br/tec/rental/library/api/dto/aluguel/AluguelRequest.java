package br.tec.rental.library.api.dto.aluguel;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@AllArgsConstructor
public class AluguelRequest {
    @NotNull(message = "{notnull}")
    private Long locatarioID;
    @NotEmpty(message = "{arrayid.notempty}")
    private List<Long> livrosID;
    private LocalDate prevista_devolucao;
}
