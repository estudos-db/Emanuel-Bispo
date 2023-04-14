package model;

import java.math.BigDecimal;

public class Produto {
    private final int id;
    private final String nome;
    private BigDecimal preco;
    private Integer quantidadeEmEstoque;


    public Produto(int id, String nome, BigDecimal preco, Integer quantidadeEmEstoque) {
        this.id = id;
        this.nome = nome;
        this.preco = preco;
        this.quantidadeEmEstoque = quantidadeEmEstoque;
    }

    public int getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidadeEmEstoque() {
        return quantidadeEmEstoque;
    }

    public boolean subtraiQuantidadeDoEstoque(int quantidade) {
        quantidadeEmEstoque -= quantidade;
        return true;
    }

    @Override
    public String toString() {
        return nome;
    }

    @Override
    public boolean equals(Object obj) {
        return this.toString().equals(obj.toString());
    }
}
