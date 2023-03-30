package util;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GeradorCodigoEmprestimoTest {
    @Test
    void geraDeveRetornarCodigoAleatorio() {
        String codigo1 = GeradorCodigoEmprestimo.gera();
        String codigo2 = GeradorCodigoEmprestimo.gera();

        assertEquals(6, codigo1.length());
        assertEquals(6, codigo2.length());
        assertNotEquals(codigo1, codigo2);
    }
}
