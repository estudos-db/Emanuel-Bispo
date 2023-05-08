package br.tec.rental.library.api.dto.aluguel;

import br.tec.rental.library.api.dto.livro.LivroResume;
import br.tec.rental.library.api.dto.locatario.LocatarioResume;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter @Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class AluguelResponse {
    private Long id;
    private LocatarioResume locatario;
    private List<LivroResume> livros;
    private LocalDate data;
    private LocalDate prevista_devolucao;
    private LocalDate devolucao;
}
