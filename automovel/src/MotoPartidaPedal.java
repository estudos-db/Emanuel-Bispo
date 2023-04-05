public class MotoPartidaPedal extends Moto {
    public MotoPartidaPedal(int numeroRodas, int velocidadeMax) {
        super(numeroRodas, velocidadeMax);
    }

    @Override
    public void ligar() {
        if (super.isAceleradorPressionado()) {
            super.ligar();
        }else {
            throw new RuntimeException("Para pode ligar, o acelerador precisa estar pressionado!");
        }
    }
}
