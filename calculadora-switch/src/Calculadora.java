import java.util.Scanner;

public class Calculadora {

    public static Scanner sc = new Scanner(System.in);

    public String iniciar() {

        System.out.println("Escolha a operação:\n+\n-\n/\n*");
        String operacao = sc.nextLine();

        System.out.println("Digite o primerio numero: ");
        Integer numero1 = sc.nextInt();

        System.out.println("Digite o segundo numero: \n");
        Integer numero2 = sc.nextInt();


        String mensagemResultado = "Resultado da operação: ";

        switch(operacao) {
            case "+":
                return mensagemResultado + somar(numero1, numero2);

            case "-":
                return mensagemResultado + subtrair(numero1, numero2);

            case "/":
                return mensagemResultado + dividir(numero1, numero2);

            case "*":
                return mensagemResultado + multiplicar(numero1, numero2);

            default:
                return "Ocorreu um erro na operação!";
        }

    }

    public static int somar(int numero1, int numero2) {
        return numero1 + numero2;
    }

    public static int subtrair(int numero1, int numero2) {
        return numero1 - numero2;
    }

    public static int dividir(int numero1, int numero2) {
        return numero1 / numero2;
    }

    public static int multiplicar(int numero1, int numero2) {
        return numero1 * numero2;
    }

}

