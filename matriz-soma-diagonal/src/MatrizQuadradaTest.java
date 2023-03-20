import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MatrizQuadradaTest {

    private MatrizQuadrada mat = new MatrizQuadrada();
    private int[][] matrizAtual = new int[][]
                                    { {12,  4,  7,  9},
                                      {5,  45, 76,  8},
                                      {33, 16,  3,  6},
                                      {28,  1,  2, 11} };


    @Test
    void deveSomarDiagonalPrincipalDaMatriz() {
        assertEquals(71, mat.somaDiagonalPrincipal(matrizAtual));
    }

}
