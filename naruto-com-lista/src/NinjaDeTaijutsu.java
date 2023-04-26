import java.util.Map;

public class NinjaDeTaijutsu extends Personagem implements Ninja{
    public NinjaDeTaijutsu(String nome, Map<String, Jutsu> jutsus, int chakra, int vida) {
        super(nome, jutsus, chakra, vida);
    }

    public NinjaDeTaijutsu(String nome, Map<String, Jutsu> jutsus, int vida) {
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
        System.out.print("Você usou suas habilidades de Taijutsu!");

        return true;
    }

    @Override
    public void desviar(Jutsu jutsuOponente) {
        boolean teveSucesso = GeraResultadoDefesaAleatorio
                .getResultado(jutsuOponente.getDano());

        if(!teveSucesso) descontarVida(jutsuOponente.getDano());
    }
}
