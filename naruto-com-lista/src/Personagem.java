import java.util.HashMap;
import java.util.Map;

public abstract class Personagem {
    private String nome;
    private Map<String, Jutsu> jutsus;
    private int chakra = 100;
    private int vida;

    public Personagem(String nome, Map<String, Jutsu> jutsus, int chakra, int vida) {
        this.nome = nome;
        this.jutsus = new HashMap<>(jutsus);
        this.chakra = chakra;
        this.vida = vida;
    }

    public Personagem(String nome, Map<String, Jutsu> jutsus, int vida) {
        this.nome = nome;
        this.jutsus = jutsus;
        this.vida = vida;
    }

    public void adicionaJutsu(String nome, Jutsu jutsuNovo) {
        jutsus.put(nome, jutsuNovo);
    }

    public void descontarChakra(int pontos) {
        chakra -= pontos;
    }

    public void descontarVida(int pontos) {
        vida -= pontos;
    }

    public int getChakra() {
        return chakra;
    }

    public Map<String, Jutsu> getJutsus() {
        return jutsus;
    }
}
