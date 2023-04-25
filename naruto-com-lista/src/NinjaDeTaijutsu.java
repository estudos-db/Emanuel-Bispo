import java.util.Map;
import java.util.Random;

public class NinjaDeTaijutsu extends Personagem implements Ninja{
    public NinjaDeTaijutsu(String nome, Map<String, Jutsu> jutsus, int chakra, int vida) {
        super(nome, jutsus, chakra, vida);
    }

    public NinjaDeTaijutsu(String nome, Map<String, Jutsu> jutsus, int vida) {
        super(nome, jutsus, vida);
    }

    @Override
    public Jutsu usarJutsu(String nome) {
        Jutsu jutsu = getJutsus().get(nome);
        if(getChakra() > 0){
            super.descontarChakra(jutsu.getConsumoDeChakra());
            System.out.print("Você usou suas habilidades de Taijutsu!");
        }else {
            System.out.print("Você não possui chakra suficiente para poder atacar!");
        }

        return jutsu;
    }

    @Override
    public void desviar(Jutsu jutsuOponente) {
        boolean teveSucesso = GeraResultadoDefesaAleatorio
                .getResultado(jutsuOponente.getDano());

        if(!teveSucesso) descontarVida(jutsuOponente.getDano());
    }
}
