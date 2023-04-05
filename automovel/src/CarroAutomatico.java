public class CarroAutomatico extends Carro{
    public CarroAutomatico(int numeroRodas, int velocidadeMax) {
        super(numeroRodas, velocidadeMax);
    }

    @Override
    public void ligar() {
        if(super.isFreioPressionado()) {
            super.ligar();
        }else {
            throw new RuntimeException("Para pode ligar, o freio precisa estar pressionado!");
        }
    }

}
