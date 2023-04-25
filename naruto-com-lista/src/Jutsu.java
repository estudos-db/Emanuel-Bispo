public class Jutsu {
    private int dano;
    private int consumoDeChakra;

    public Jutsu(int dano, int consumoDeChakra) {
        this.dano = dano;
        this.consumoDeChakra = consumoDeChakra;
    }

    public int getConsumoDeChakra() {
        return consumoDeChakra;
    }

    public int getDano() {
        return dano;
    }
}
