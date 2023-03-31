import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

public class CalculadoraTest {
    @Test
    void deveRetornarASomaDeDoisComTres() {
        assertEquals(BigDecimal.valueOf(5),
                Calculadora.somar(BigDecimal.valueOf(2), BigDecimal.valueOf(3))
        );
    }

    @Test
    void deveRetornarASubtracaoDeCincoComTres() {
        assertEquals(BigDecimal.valueOf(2),
                Calculadora.subtrair(BigDecimal.valueOf(5), BigDecimal.valueOf(3))
        );
    }

    @Test
    void deveRetornarADivisaoDeOitoComDois() {
        assertEquals(BigDecimal.valueOf(4),
                Calculadora.dividir(BigDecimal.valueOf(8), BigDecimal.valueOf(2))
        );
    }

    @Test
    void deveRetornarAMultiplicacaoDeQuatroComCinco() {
        assertEquals(BigDecimal.valueOf(20),
                Calculadora.multiplicar(BigDecimal.valueOf(4), BigDecimal.valueOf(5)));
    }

    @Test
    void deveRetornarAPotenciacao() {
        assertEquals(BigDecimal.valueOf(16),
                Calculadora.potencia(BigDecimal.valueOf(4), BigDecimal.valueOf(2))
        );
        assertEquals(BigDecimal.valueOf(125),
                Calculadora.potencia(BigDecimal.valueOf(5), BigDecimal.valueOf(3))
        );
    }

}
