public abstract class Automovel {

    private final int numeroRodas;
    private final int velocidadeMax;
    private boolean isLigado;
    private boolean isFreioPressionado;
    private boolean isAceleradorPressionado;

    public Automovel(int numeroRodas, int velocidadeMax) {
        this.numeroRodas = numeroRodas;
        this.velocidadeMax = velocidadeMax;
        this.isLigado = false;
        this.isFreioPressionado = false;
        this.isAceleradorPressionado = false;
    }

    public void ligar() {
        this.isLigado = true;
    }

    public void desligar(){
        this.isLigado = false;
    }

    public void pressionarFreio() {
        this.isFreioPressionado = true;
    }

    public void soltarFreio() {
        this.isFreioPressionado = false;
    }

    public void pressionarAcelerador() {
        this.isAceleradorPressionado = true;
    }

    public void soltarAcelerador() {
        this.isAceleradorPressionado = false;
    }

    public boolean isFreioPressionado() {
        return isFreioPressionado;
    }

    public boolean isAceleradorPressionado() {
        return isAceleradorPressionado;
    }

    public boolean isLigado() {
        return isLigado;
    }

}
