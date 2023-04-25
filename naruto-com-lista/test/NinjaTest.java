import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class NinjaTest {
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    private NinjaDeTaijutsu ninjaDeTaijutsu;

    @BeforeEach
    void iniciaNinjas() {
       ninjaDeTaijutsu = new NinjaDeTaijutsu(
               "Ninja1", Map.of("jutsuJ1", new Jutsu(6, 5)), 10, 10
       );
       System.setOut(new PrintStream(outContent));
    }

    @Test
    void deveAtacarComSucesso() {
        ninjaDeTaijutsu.usarJutsu("jutsuJ1");
        assertEquals("Você usou suas habilidades de Taijutsu!", outContent.toString());
    }

    @Test
    void deveFalharAoAtacarComChakraZero() {
        ninjaDeTaijutsu.descontarChakra(10);
        assertEquals(0, ninjaDeTaijutsu.getChakra());
        ninjaDeTaijutsu.usarJutsu("jutsuJ1");
        assertEquals("Você não possui chakra suficiente para poder atacar!", outContent.toString());
    }
}
