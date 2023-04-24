import java.util.List;

public class NinjaDeGenjutsu extends Personagem implements Ninja{
    public NinjaDeGenjutsu(String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        super(nome, idade, aldeia, jutsus, chakra);
    }

    @Override
    public void usarJutsu() {
        System.out.println("Você usou sua habilidade Genjutsu!");
    }

    @Override
    public void desviar() {
        System.out.println("Você desviou de um golpe usando suas habilidades de Genjutsu!");
    }
}
