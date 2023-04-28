import java.util.Random;

public class GeraResultadoDefesaAleatorio {
    public static boolean getResultado(int oponenteDano) {
        boolean conseguiuDesviar = new Random().nextBoolean();

        if(conseguiuDesviar){
            System.out.println("Você desviou de um ataque!");
        }else {
            System.out.println("Você tentou desviar de um ataque, mas falhou! Dano sofrido: " + oponenteDano);
        }

        return conseguiuDesviar;
    }
}
