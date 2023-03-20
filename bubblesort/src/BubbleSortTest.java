import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class BubbleSortTest {

    private BubbleSort bs = new BubbleSort();
    private int[] vetAtual = new int[] {6, 2, 9, 7};

    @Test
    void deveRetornarVetorOrdenado() {
        int[] vetEsperado = new int[] {2, 6, 7, 9};
        assertArrayEquals(vetEsperado, bs.ordenar(vetAtual));
    }

}
