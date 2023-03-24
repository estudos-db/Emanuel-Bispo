import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UnirOrdenarVetorTest {

    private UnirOrdenarVetor uneVet = new UnirOrdenarVetor();
    private int[] vet1 = new int[] {8, 3, 1};
    private int[] vet2 = new int[] {6, 10, 5};

    @Test
    void deveOrdenarOVetor() {
        assertArrayEquals(new int[] {1, 3, 8}, uneVet.ordenaVetor(vet1));
        assertArrayEquals(new int[] {5, 6, 10}, uneVet.ordenaVetor(vet2));
    }

    @Test
    void deveUnirEOrdenarOsVetores() {
        assertArrayEquals(new int[] {1, 3, 5, 6, 8, 10},
                uneVet.unirOrdenarVetores(vet1, vet2));
    }

}
