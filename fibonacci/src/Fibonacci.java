public class Fibonacci {
    public void gerarSequencia(int numero) {

        int soma = 0;
        int anterior = 0;
        int proximo = 1;

        for (int i = 0; i < numero +1; i++) {
            System.out.print(anterior + ", ");

            soma = anterior + proximo;
            anterior = proximo;
            proximo = soma;
        }

    }
}
