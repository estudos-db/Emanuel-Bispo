package model;

import java.math.BigDecimal;
import java.util.ArrayList;

public class Estoque {
    private final int id;
    private ArrayList<Produto> listaDeProdutos;

    public Estoque(ArrayList<Produto> listaDeProdutos) {
        this.id = 0;
        this.listaDeProdutos = listaDeProdutos;
    }

    public Produto buscaProdutoPorNome(String nome) {
        return listaDeProdutos.stream()
                .filter(produto -> produto.equals(nome))
                .findFirst().orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public Produto buscaProdutoPorId(int id) {
        return listaDeProdutos.stream()
                .filter(produto -> produto.getId() == id)
                .findFirst().orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    public boolean cadastraProduto(String nome, BigDecimal preco, int quantidade) {
        var idBaseadoNoTamanhoDaLista = listaDeProdutos.size() + 1;
        return listaDeProdutos.add(new Produto(idBaseadoNoTamanhoDaLista, nome, preco, quantidade));
    }

    public void imprimeCatalogoEstoque() {
        System.out.println(listaDeProdutos.toString());
    }

    public void darBaixaPorNome(String nomeProduto, int quantidadeDaBaixa) {
        listaDeProdutos.stream()
                .filter(produto -> produto.equals(nomeProduto))
                .findFirst()
                .map(produto -> {
                    listaDeProdutos.remove(produto);
                    return produto.subtraiQuantidadeDoEstoque(quantidadeDaBaixa);
                })
                .orElseThrow(() -> new RuntimeException("Produto inválido ou sem estoque!"));
    }

    public void darBaixaPorId(int idProduto, int quantidadeDaBaixa) {
        listaDeProdutos.stream()
                .filter(produto -> produto.getId() == idProduto)
                .findFirst()
                .map(produto -> {
                    listaDeProdutos.remove(produto);
                    return produto.subtraiQuantidadeDoEstoque(quantidadeDaBaixa );
                })
                .orElseThrow(() -> new RuntimeException("Produto inválido ou sem estoque!"));
    }

    public int getQuantidadeProdutoNoEstoque(String nome) {
        Produto produtoEncontrado = listaDeProdutos.stream()
                .filter(produto -> produto.equals(nome))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));


        return produtoEncontrado.getQuantidadeEmEstoque();
    }

    public int getTamanhoVariedadeProdutosNoEstoque() {
        return listaDeProdutos.size();
    }

    public int getPosicaoProdutoNaLista(String nome) {
        for (Produto produto: listaDeProdutos) {
            if (produto.equals(nome)) return listaDeProdutos.indexOf(produto);
        }

        return -1;
    }

    public boolean temEstoque(String nome) {
        return listaDeProdutos.stream()
                .filter(produto -> produto.equals(nome))
                .findFirst()
                .map(produto -> produto.getQuantidadeEmEstoque() > 0)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado"));
    }

}
