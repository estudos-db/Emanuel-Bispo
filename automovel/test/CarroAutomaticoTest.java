import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class CarroAutomaticoTest {

    private CarroAutomatico carroAutomatico = new CarroAutomatico(220);

    @Test
    void ligarDeveLancarExcecaoAoTentarSemPressionarOFreio() {
        assertFalse(carroAutomatico.isLigado());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> carroAutomatico.ligar()
        );
        assertEquals("Para pode ligar, o freio precisa estar pressionado!", ex.getMessage());
    }

    @Test
    void deveLigar() {
        assertFalse(carroAutomatico.isLigado());

        carroAutomatico.pressionarFreio();
        carroAutomatico.ligar();

        assertTrue(carroAutomatico.isLigado());
    }

}
