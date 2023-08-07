package br.tec.rental.library.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Aluguel {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    @JoinColumn(name = "locatario_id")
    private Locatario locatario;
    @ManyToMany
    @JoinTable(
            name = "aluguel_livro",
            joinColumns = @JoinColumn(name = "aluguel_id"),
            inverseJoinColumns = @JoinColumn(name = "livro_id")
    )
    private Set<Livro> livros;
    private LocalDate data = LocalDate.now();
    private LocalDate prevista_devolucao = LocalDate.now().plusDays(2L);
    private LocalDate devolucao;
}
