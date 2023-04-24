import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class NinjaTest {

    private Ninja ninja = new Ninja("Ninja", 20, "Aldeia", new ArrayList<>(List.of("Jutsu")), 200);

    @Test
    void aumentarChakraDeveAumentarEmDois() {
        assertEquals(200, ninja.getChakra());
        ninja.aumentarChakra();
        assertEquals(202, ninja.getChakra());
    }

    @Test
    void adicionaJutsuDeveAdicionarComSucesso() {
        assertEquals(1, ninja.getJutsusListSize());
        ninja.adicionaJutsu("Novo jutsu");
        assertEquals(2, ninja.getJutsusListSize());
    }

    class Ninja extends Personagem{
        public Ninja(String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
            super(nome, idade, aldeia, jutsus, chakra);
        }
    }
}
