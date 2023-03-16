import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class ValidaMaiorDeIdadeTest {

    private ValidaMaiorDeIdade validaMaiorDeIdade = new ValidaMaiorDeIdade();

    @Test
    void deveRetornaMaiorDeIdadeParaIdadeVinteECinco() {
        assertEquals("Você é maior de idade", validaMaiorDeIdade.verificar(25));
    }

    @Test
    void deveRetornaMenorDeIdadeParaIdadeQuatorze() {
        assertEquals("Você é menor de idade", validaMaiorDeIdade.verificar(14));
    }

}
