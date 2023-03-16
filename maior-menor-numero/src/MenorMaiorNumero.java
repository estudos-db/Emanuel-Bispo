import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MenorMaiorNumero {

    private Random random = new Random();
    private List<Integer> numeros = new ArrayList<>();

    public void geraNumeros() {
        for(int i = 0; i < 10; i++) {
            int numeroGerado = random.nextInt(100);
            numeros.add(numeroGerado);
        }
    }

    public Integer obterMaiorNumero(List<Integer> numerosList) {
        int auxMaior = numerosList.get(0);
        for (int i = 0; i < numerosList.size(); i++)
            if(numerosList.get(i) > auxMaior) auxMaior =  numerosList.get(i);

        return auxMaior;
    }

    public Integer obterMenorNumero(List<Integer> numerosList) {
        int auxMenor  = numerosList.get(0);
        for (int i = 0; i < numerosList.size(); i++)
            if(numerosList.get(i) < auxMenor) auxMenor =  numerosList.get(i);

       return auxMenor;
    }

    public void imprimirResultado() {
        for (int i = 0; i < numeros.size(); i++) {
            System.out.print(numeros.get(i)+ " ");
        }

        System.out.print("\nNumero menor: " + obterMenorNumero(numeros) +
                " Numero maior: " + obterMaiorNumero(numeros));
    }

    public List<Integer> getNumeros() {
        return numeros;
    }

}
