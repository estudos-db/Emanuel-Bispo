import java.util.Random;

public class BubbleSort {

    private int[] vetor = new int[100];

    public int[] geraVetor() {
        Random random = new Random();
        for (int i = 0; i < vetor.length; i++)
            vetor[i] = random.nextInt(100);
        return vetor;
    }

    public int[] ordenar(int[] vetor) {
        int aux;
        for (int i = 0; i < vetor.length; i++) {
            for (int j = 0; j < vetor.length - 1; j++){
                if (vetor[j] > vetor[j + 1]){
                    aux = vetor[j];
                    vetor[j] = vetor[j + 1];
                    vetor[j + 1] = aux;
                }
            }
        }
        return vetor;
    }

}
