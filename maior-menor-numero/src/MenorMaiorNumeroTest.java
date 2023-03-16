import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MenorMaiorNumeroTest {

    private MenorMaiorNumero menorMaiorNumero = new MenorMaiorNumero();
    private List<Integer> numerosList = new ArrayList<>(Arrays.asList(23, 65, 78, 4, 20, 38, 44, 60, 16, 50));

    @Test
    @DisplayName("Deve retornar 78 como o maior numero")
    void deveRetornarMaiorNumero() {
        assertEquals(78, menorMaiorNumero.obterMaiorNumero(numerosList));
    }

    @Test
    @DisplayName("Deve retornar 4 como o menor numero")
    void deveRetornarMenorNumero() {
        assertEquals(4, menorMaiorNumero.obterMenorNumero(numerosList));
    }

    @Test
    void deveGerarUmaListaDeNumerosAleatoriosComTamanhoDez() {
        menorMaiorNumero.geraNumeros();
        List<Integer> numerosGerados = menorMaiorNumero.getNumeros();

        assertTrue(numerosGerados.size() == 10);
    }

}
