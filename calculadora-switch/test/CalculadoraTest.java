import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    @Test
    void deveRetornarASomaDeDoisComTres() {
        assertEquals(5, Calculadora.somar(2, 3));
    }

    @Test
    void deveRetornarASubtracaoDeCincoComTres() {
        assertEquals(2, Calculadora.subtrair(5, 3));
    }

    @Test
    void deveRetornarADivisaoDeOitoComDois() {
        assertEquals(4, Calculadora.dividir(8, 2));
    }

    @Test
    void deveRetornarAMultiplicacaoDeQuatroComCinco() {
        assertEquals(20, Calculadora.multiplicar(4, 5));
    }

}
