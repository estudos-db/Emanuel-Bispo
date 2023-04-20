package service;

import model.Estoque;
import model.Item;
import model.Pedido;
import model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Scanner;

public class PedidoService {

    private Scanner scanner;
    private Pedido pedido;
    private Estoque estoque;
    private BigDecimal valorFinal;

    public PedidoService(Estoque estoque) {
        this.pedido = new Pedido(new ArrayList<>());
        this.estoque = estoque;
        this.valorFinal = BigDecimal.ZERO;
        scanner = new Scanner(System.in);
    }

    public void iniciarPedido() {
        var opcao = 0;
        var continuarFluxo = false;
        do {

            if(continuarFluxo) {
                opcao = escolheOpcao();
                if(opcao > 0 && opcao <= 4) {
                    continuarFluxo = false;
                }
            } else {

                System.out.println("Digite o nome do produto desejado: ");
                var nomeProdutoEscolhidoDoPedido = pedido.recebeNomeDoTeclado();


                try {
                    Produto produto = estoque.buscaProdutoPorNome(nomeProdutoEscolhidoDoPedido);

                    System.out.println("Digite a quantidade desejada: ");
                    var quantidadeDoProduto = pedido.recebeQuantidadeDoTeclado();

                    if(estoque.temEstoque(produto.toString()) &&
                            estoque.getQuantidadeProdutoNoEstoque(produto.toString()) >= quantidadeDoProduto
                    ) {
                        pedido.adicionaItemNaLista(new Item(produto, quantidadeDoProduto));
                    } else {
                        System.out.println("Estoque insuficiente ou produto não disponível!");
                    }

                }catch (Exception ex) {
                    System.out.println(ex.getMessage());
                }finally {
                    opcao = escolheOpcao();
                }
            }

            switch (opcao) {
                case 1:
                    break;

                case 2:
                    pedido.limparCarrinho();
                    System.out.println("Carrinho reiniciado!");
                    break;

                case 3:
                    this.finalizaPedido();
                    continuarFluxo = true;
                    break;

                case 4:
                    pedido.limparCarrinho();
                    System.out.println("Saindo...");
                    break;

                default:
                    continuarFluxo = true;
                   break;
            }

        } while (opcao != 4);
    }

    public void finalizaPedido() {
        pedido.imprimePedido();
        pedido.imprimeValorTotal();

        valorFinal = pedido.getValorTotal();

        System.out.println("Informe a quantia fornecida: ");
        var valorRecebido = scanner.nextBigDecimal();

        var troco = TrocoService.calculaTrocoEmDinheiro(valorFinal, valorRecebido);
        var repassarNotas = TrocoService.defineMenorQuantidadeDeNotas(troco);

        var itens = pedido.getListaDeItens();

        for (Item item : itens) {
            estoque.darBaixaPorNome(item.getProduto().getNome(), item.getQuantidade());
        }

        System.out.println("Para troco: R$ " + troco + ", repassar: "+ repassarNotas);
        System.out.println("Pedido finalizado!\n");
        pedido = new Pedido(new ArrayList<>());
    }

    private int escolheOpcao() {
        System.out.println("Escolha como deseja prosseguir: ");
        System.out.println("1- Adicionar novo produto ao pedido");
        System.out.println("2- Reiniciar carrinho");
        System.out.println("3- Finalizar pedido");
        System.out.println("4- Sair");

        int escolha = scanner.nextInt();
        if(escolha == 3 && pedido.getListaDeItens().size() == 0){
            System.out.println("\nPedido vazio! Adicione itens a lista para poder continuar!\n");
            return -1;
        }

       return escolha;
    }

}
