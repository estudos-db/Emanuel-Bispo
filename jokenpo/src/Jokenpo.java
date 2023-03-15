import java.util.Random;

public class Jokenpo {

    private Opcao geraJogadaAdversaria() {
        Random random = new Random();
        return Opcao.values()[random.nextInt(3)];
    }

    public void jogar(Opcao entrada) {

        Opcao escolhaJogador = entrada;

        Opcao jogadaAdversaria = geraJogadaAdversaria();

        String jogadaResultante = String.format("Você: %s x Adversario: %s",
                escolhaJogador.name(), jogadaAdversaria.name());

        if(escolhaJogador.equals(jogadaAdversaria)){
            System.out.println("O jogo empatou! " + jogadaResultante);
        } else if (escolhaJogador.vence(jogadaAdversaria)) {
            System.out.println("Venceu! " + jogadaResultante);
        } else {
            System.out.println("Jogo perdido! " + jogadaResultante);
        }

    }

}
