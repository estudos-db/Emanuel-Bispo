import java.util.Map;

public class NinjaDeNinjutsu extends Personagem implements Ninja{
    public NinjaDeNinjutsu(String nome, Map<String, Jutsu> jutsus, int chakra, int vida) {
        super(nome, jutsus, chakra, vida);
    }

    public NinjaDeNinjutsu(String nome, Map<String, Jutsu> jutsus, int vida) {
        super(nome, jutsus, vida);
    }

    @Override
    public boolean usarJutsu(String nome) {
        Jutsu jutsu = getJutsus().get(nome);

        if(!VerificaSePossuiChakra.getResultado(getChakra(), jutsu.getConsumoDeChakra())){
            System.out.print("Você não possui chakra suficiente para poder atacar!");
            return false;
        }

        super.descontarChakra(jutsu.getConsumoDeChakra());
        System.out.print("Você usou suas habilidades de Ninjutsu!");

        return true;
    }

    @Override
    public void desviar(Jutsu jutsuOponente) {
        boolean teveSucesso = GeraResultadoDefesaAleatorio
                .getResultado(jutsuOponente.getDano());

        if(!teveSucesso) descontarVida(jutsuOponente.getDano());
    }
}
