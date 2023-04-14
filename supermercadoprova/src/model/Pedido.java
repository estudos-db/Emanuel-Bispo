package model;

import java.math.BigDecimal;
import java.util.*;


public class Pedido {

    private ArrayList<Item> listaDeItens;
    private BigDecimal valorTotal;
    private Scanner scanner;

    public Pedido(ArrayList<Item> listaDeItens) {
        scanner = new Scanner(System.in);
        this.listaDeItens = listaDeItens;
        this.valorTotal = BigDecimal.ZERO;
    }

    public void calculaValorTotal() {
        for (Item item : listaDeItens) {
            valorTotal = valorTotal.add(item.getValorDoItem());
        }
    }

    public String recebeNomeDoTeclado() {
        return scanner.next();
    }

    public int recebeQuantidadeDoTeclado() {
        return scanner.nextInt();
    }

    public void adicionaItemNaLista(Item item) {
        listaDeItens.add(item);
    }

    public void limparCarrinho() {
        listaDeItens.clear();
        valorTotal = BigDecimal.ZERO;
    }

    public void imprimePedido() {
        System.out.println("\n");
        System.out.println(this);
    }

    public void imprimeValorTotal() {
        calculaValorTotal();
        System.out.println("Valor total: " + valorTotal);
    }

    public List<Item> getListaDeItens() {
        return Collections.unmodifiableList(listaDeItens);
    }

    public BigDecimal getValorTotal() {
        return valorTotal;
    }

    @Override
    public String toString() {
        return listaDeItens.toString();
    }

}
