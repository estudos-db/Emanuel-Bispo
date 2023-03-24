import java.util.Random;

public class UnirOrdenarVetor {

    private int[] vetor1, vetor2;

    public UnirOrdenarVetor() {
        this.vetor1 = new int[50];
        this.vetor2 = new int[50];
    }

    public void geraVetores() {
        Random random = new Random();
        for (int i = 0; i < vetor1.length; i++)
            vetor1[i] = random.nextInt(100);

        for (int i = 0; i < vetor2.length; i++)
            vetor2[i] = random.nextInt(100);
    }

    public int[] ordenaVetor(int[] vetor) {
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

    public int[] unirOrdenarVetores(int[] vetor1, int[] vetor2) {

        int count = 0;
        int[] novoVetor = new int[vetor1.length + vetor2.length];

        for (int i = 0; i < vetor1.length; i++)
            novoVetor[count++] = vetor1[i];

        for (int i = 0; i < vetor2.length; i++)
            novoVetor[count++] = vetor2[i];

        novoVetor = ordenaVetor(novoVetor);

        return novoVetor;

    }

    public int[] getVetor1() {
        return vetor1;
    }

    public int[] getVetor2() {
        return vetor2;
    }

}
