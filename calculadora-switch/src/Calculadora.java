import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

public class Calculadora {

    public static Scanner sc = new Scanner(System.in);

    public String iniciar() {

        System.out.println("Escolha a operação:\n+\n-\n/\n*\n^");
        String operacao = sc.nextLine();

        BigDecimal numero1 = BigDecimal.ZERO;
        BigDecimal numero2 = BigDecimal.ZERO;
        BigDecimal base = BigDecimal.ZERO;
        BigDecimal expoente = BigDecimal.ZERO;

        if(operacao.equals("^")){
            System.out.println("Digite a base: \n");
            base = sc.nextBigDecimal();

            System.out.println("Digite o expoente: \n");
            expoente = sc.nextBigDecimal();
        }else {

            System.out.println("Digite o primerio numero: ");
            numero1 = sc.nextBigDecimal();

            System.out.println("Digite o segundo numero: \n");
            numero2 = sc.nextBigDecimal();

        }

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

            case "^":
                return mensagemResultado + potencia(base, expoente);

            default:
                return "Ocorreu um erro na operação!";
        }

    }

    public static BigDecimal somar(BigDecimal numero1, BigDecimal numero2) {
        return numero1.add(numero2);
    }

    public static BigDecimal subtrair(BigDecimal numero1, BigDecimal numero2) {
        return numero1.subtract(numero2);
    }

    public static BigDecimal dividir(BigDecimal numero1, BigDecimal numero2) {
        return numero1.divide(numero2);
    }

    public static BigDecimal multiplicar(BigDecimal numero1, BigDecimal numero2) {
        return numero1.multiply(numero2);
    }

    public static BigDecimal potencia(BigDecimal base, BigDecimal expoente) {
        if(expoente.equals(BigDecimal.valueOf(0))) return BigDecimal.ONE;
        return base.multiply(potencia(base, expoente.subtract(BigDecimal.valueOf(1))));
    }

}

