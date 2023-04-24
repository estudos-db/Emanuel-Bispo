import java.util.List;

public abstract class Personagem {
    private String nome;
    private int idade;
    private String aldeia;
    private List<String> jutsus;
    private int chakra;

    public Personagem(String nome, int idade, String aldeia, List<String> jutsus, int chakra) {
        this.nome = nome;
        this.idade = idade;
        this.aldeia = aldeia;
        this.jutsus = jutsus;
        this.chakra = chakra;
    }

    public void adicionaJutsu(String jutsu) {
        jutsus.add(jutsu);
    }

    public void aumentarChakra() {
        chakra += 2;
    }

    public void infoPersonagem() {
        System.out.println( "Nome: " + nome + "\n" +
                            "Idade: " + idade + "\n" +
                            "Aldeia: " + aldeia + "\n" +
                            "Chakra: " + chakra + "\n" +
                            "Jutsus: " + jutsus.toString() );
    }

    public int getChakra() {
        return chakra;
    }

    public int getJutsusListSize() {
        return jutsus.size();
    }
}
