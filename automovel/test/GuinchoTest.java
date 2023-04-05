import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class GuinchoTest {

    private Guincho guincho = new Guincho(4, 180, 8);

    private Automovel moto1 = new Moto(2, 200);
    private Automovel moto2 = new Moto(2, 150);
    private Automovel moto3 = new Moto(2, 100);
    private Automovel moto4 = new Moto(2, 180);

    private Automovel carro1 = new Carro(4, 200);
    private Automovel carro2 = new Carro(4, 280);

    @Test
    void guinchoEspacoPontosOitoDeveSuportarGuincharQuatroMotos() {
        assertEquals(0, guincho.quantidadeGuinchado());
        guincho.guinchar(moto1);
        guincho.guinchar(moto2);
        guincho.guinchar(moto3);
        guincho.guinchar(moto4);

        assertEquals(4, guincho.quantidadeGuinchado());
    }


    @Test
    void guinchoEspacoPontosOitoDeveSuportarGuincharUmCarroMaisUmaMoto() {
        assertEquals(0, guincho.quantidadeGuinchado());
        guincho.guinchar(moto1);
        guincho.guinchar(carro1);

        assertEquals(2, guincho.quantidadeGuinchado());

        assertThrows(RuntimeException.class, () -> guincho.guinchar(moto1));
        assertThrows(RuntimeException.class, () -> guincho.guinchar(carro1));
    }

    @Test
    void guinchoEspacoPontosOitoDeveLancarExececaoParaGuinchoSemEspaco() {
        guincho.guinchar(carro1);
        RuntimeException ex = assertThrows(RuntimeException.class,
                () -> guincho.guinchar(carro2)
        );

        assertEquals("Não há espaço para mais veículo", ex.getMessage());
    }

}
