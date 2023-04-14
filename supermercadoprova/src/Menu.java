import model.Estoque;
import service.PedidoService;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    private Scanner scanner;
    private Estoque estoque;
    private final PedidoService pedidoService;

    public Menu() {
        scanner = new Scanner(System.in);
        estoque = new Estoque(new ArrayList<>());
        pedidoService = new PedidoService(estoque);
    }

    public void controleMenu() {
        var opcao = 0;

        do {

            System.out.println("\nInforme qual das opções deseja executar: ");
            System.out.println("1: Ver estoque");
            System.out.println("2: realizar pedido");
            System.out.println("3: cadastrar produto");
            System.out.println("4: encerrar o programa\n");

            opcao = scanner.nextInt();

            switch (opcao){
                case 1:
                    estoque.imprimeCatalogoEstoque();
                    break;

                case 2:
                    pedidoService.iniciarPedido();
                    break;

                case 3:
                    System.out.println("Informe o nome do produto: ");
                    var nomeFornecido = scanner.next();

                    System.out.println("Informe o preço do produto: ");
                    var precoFornecido = scanner.nextBigDecimal();

                    System.out.println("Informe a quantidade do produto: ");
                    var quantidadeInformada = scanner.nextInt();
                    estoque.cadastraProduto(nomeFornecido, precoFornecido, quantidadeInformada);
                    System.out.println("Cadastro realizado!");
                    break;

                case 4:
                    System.out.println("Encerrando programa");
                    break;

                default:
                    System.out.println("Opção inválida! O programa será abortado");
            }

        }while (opcao != 4);
    }
}
