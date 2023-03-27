package modelo;

import java.time.LocalDate;

public class Pessoa {

    private String nome;
    private String cpf;
    private LocalDate data_nascimento;
    private String telefone;

    public Pessoa(String nome, String cpf, LocalDate data_nascimento, String telefone) {
        this.nome = nome;
        this.cpf = cpf;
        this.data_nascimento = data_nascimento;
        this.telefone = telefone;
    }

    public String getNome() {
        return nome;
    }

    public String getCpf() {
        return cpf;
    }

    public LocalDate getData_nascimento() {
        return data_nascimento;
    }

    public String getTelefone() {
        return telefone;
    }

}
