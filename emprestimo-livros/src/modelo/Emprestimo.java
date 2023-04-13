package modelo;

import java.time.LocalDate;

public class Emprestimo {

    private String codigo;
    private Livro livro;
    private Pessoa pessoa;
    private LocalDate data_emprestimo;
    private LocalDate data_prev_devolucao;
    private LocalDate data_devolucao;


    public Emprestimo(String codigo, Livro livro, Pessoa pessoa,
                      LocalDate data_emprestimo, LocalDate data_prev_devolucao
    ) {
        this.codigo = codigo;
        this.livro = livro;
        this.pessoa = pessoa;
        this.data_emprestimo = data_emprestimo;
        this.data_prev_devolucao = data_prev_devolucao;
        this.data_devolucao = null;
    }

    public Livro getLivro() {
        return livro;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public LocalDate getData_emprestimo() {
        return data_emprestimo;
    }

    public String getCodigo() {
        return codigo;
    }

    public LocalDate getData_devolucao() {
        return data_devolucao;
    }

    public void setData_devolucao(LocalDate data_devolucao) {
        this.data_devolucao = data_devolucao;
    }

}
