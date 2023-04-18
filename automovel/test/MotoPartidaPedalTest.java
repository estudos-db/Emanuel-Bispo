import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class MotoPartidaPedalTest {

    private MotoPartidaPedal motoPartidaPedal = new MotoPartidaPedal(220);

    @Test
    void ligarDeveLancarExcecaoAoTentarSemPressionarOAcelerador() {
        assertFalse(motoPartidaPedal.isLigado());

        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> motoPartidaPedal.ligar()
        );
        assertEquals("Para pode ligar, o acelerador precisa estar pressionado!", ex.getMessage());
    }

    @Test
    void deveLigar() {
        assertFalse(motoPartidaPedal.isLigado());

        motoPartidaPedal.pressionarAcelerador();
        motoPartidaPedal.ligar();

        assertTrue(motoPartidaPedal.isLigado());
    }

}
