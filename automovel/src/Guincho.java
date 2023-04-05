import util.Validacao;

import java.util.ArrayList;
import java.util.List;

public class Guincho extends Automovel {

    private List<Automovel> guinchado;
    private final int pontosEspacoTotal;
    private int pontosEspacoAtual;
    private final int pontosEspacoCarro;
    private final int pontosEspacoMoto;

    public Guincho(int numeroRodas, int velocidadeMax, int pontosEspacoTotal) {
        super(numeroRodas, velocidadeMax);
        this.guinchado = new ArrayList<>();
        this.pontosEspacoTotal = pontosEspacoTotal;
        this.pontosEspacoAtual = 0;
        this.pontosEspacoCarro = 6;
        this.pontosEspacoMoto = 2;
    }

    public void guinchar(Automovel automovel) {
        if (automovel instanceof Carro) {
            Validacao.isEspacoGuincho(pontosEspacoTotal, pontosEspacoAtual, pontosEspacoCarro);
            guinchado.add(automovel);
            this.pontosEspacoAtual += pontosEspacoCarro;
        } else if (automovel instanceof Moto) {
            Validacao.isEspacoGuincho(pontosEspacoTotal, pontosEspacoAtual, pontosEspacoMoto);
            guinchado.add(automovel);
            this.pontosEspacoAtual += pontosEspacoMoto;
        }
    }

    public int quantidadeGuinchado() {
        return guinchado.size();
    }

}
