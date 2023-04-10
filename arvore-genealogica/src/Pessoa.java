public class Pessoa {

    private String nome;
    private int idade;
    private Pai pai;
    private Mae mae;

    public Pessoa(String nome, int idade, Pai pai, Mae mae) {
        this.nome = nome;
        this.idade = idade;
        this.pai = pai;
        this.mae = mae;
    }

    public String getNome() {
        return nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public Pessoa getPai() {
        return pai;
    }


    public Pessoa getMae() {
        return mae;
    }

}
