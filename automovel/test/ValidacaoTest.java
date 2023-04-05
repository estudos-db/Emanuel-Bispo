import org.junit.jupiter.api.Test;
import util.Validacao;

import static org.junit.jupiter.api.Assertions.*;

public class ValidacaoTest {
    @Test
    void deveRetornarTrueParaValidarEspaco() {
        assertTrue(Validacao.isEspacoGuincho(6, 1, 2));
        assertTrue(Validacao.isEspacoGuincho(6, 2, 2));
        assertTrue(Validacao.isEspacoGuincho(6, 4, 2));
    }

    @Test
    void deveLancarExcecaoQuandoUltrapassaLimite() {
        assertTrue(Validacao.isEspacoGuincho(6, 1, 2));
        assertTrue(Validacao.isEspacoGuincho(6, 2, 2));
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> Validacao.isEspacoGuincho(6, 5, 2)
        );

        assertEquals("Não há espaço para mais veículo", ex.getMessage());
    }
}
