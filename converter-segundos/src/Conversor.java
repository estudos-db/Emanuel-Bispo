import java.util.Scanner;

public class Conversor {

    private static Scanner sc = new Scanner(System.in);

    public void lerDados() {
        System.out.println("Digite o tempo em segundos: ");
        int tempoEmSegundos = sc.nextInt();

        imprimeSegundosConvertido(tempoEmSegundos);
    }

    public void imprimeSegundosConvertido(int segundos) {
        int horasExtraidas = obterHoras(segundos);
        int minutosExtraidos = obterMinutos(segundos);
        int segundosExtraidos = obterSegundos(segundos);
        System.out.println(horasExtraidas + "h"
                        + minutosExtraidos + "m"
                         + segundosExtraidos+ "s");
    }

    public int obterHoras(int tempo) {
        return tempo / 3600;
    }

    public int obterMinutos(int tempo) {
        return (tempo % 3600) / 60;
    }

    public int obterSegundos(int tempo) {
        return (tempo % 3600) % 60;
    }

}
