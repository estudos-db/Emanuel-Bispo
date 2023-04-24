import java.util.List;

public class NinjaDeTaijutsu extends Personagem implements Ninja{
    public NinjaDeTaijutsu(String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        super(nome, idade, aldeia, jutsus, chakra);
    }

    @Override
    public void usarJutsu() {
        System.out.println("Você usou sua habilidade Taijutsu!");
    }

    @Override
    public void desviar() {
        System.out.println("Você desviou de um golpe usando suas habilidades de Taijutsu!");
    }
}
