package model;

import java.math.BigDecimal;

public class Item {

    private final Produto produto;
    private final Integer quantidade;
    private BigDecimal valorDoItem;

    public Item(Produto produto, Integer quantidade) {
        this.produto = produto;
        this.quantidade = quantidade;
        defineValorTotal();
    }

    private void defineValorTotal() {
        valorDoItem = produto.getPreco().multiply(BigDecimal.valueOf(quantidade));
    }

    public BigDecimal getValorDoItem() {
        return valorDoItem;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    @Override
    public String toString() {
        return quantidade + "-" + produto.getNome() + " R$ " + valorDoItem;
    }

}
