import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ConversorTest {

    private Conversor convet = new Conversor();
    private int tempoEmSegundos = 7023;

    @Test
    void deveExtrairAsHoras() {
        assertEquals(1, convet.obterHoras(tempoEmSegundos));
    }

    @Test
    void deveExtrairOsMinutos() {
        assertEquals(57, convet.obterMinutos(tempoEmSegundos));
    }

    @Test
    void deveExtrairOsSegundos() {
        assertEquals(3, convet.obterSegundos(tempoEmSegundos));
    }

}
