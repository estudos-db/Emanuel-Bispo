import org.junit.jupiter.api.Test;
import service.TrocoService;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TrocoServiceTest {

    @Test
    void calculaTrocoEmDinheiroDeveRetornarValorCorretamente() {
        assertEquals(BigDecimal.valueOf(12.5),
                TrocoService.calculaTrocoEmDinheiro(BigDecimal.valueOf(7.5), BigDecimal.valueOf(20))
        );
        assertEquals(BigDecimal.valueOf(10),
                TrocoService.calculaTrocoEmDinheiro(BigDecimal.valueOf(10), BigDecimal.valueOf(20))
        );
    }

    @Test
    void defineMenorQuantidadeDeNotasDeveRetornarMenorQuantidadeComSucesso() {
        assertEquals(
                List.of(
                             BigDecimal.valueOf(50),
                             BigDecimal.valueOf(5),
                             BigDecimal.valueOf(2),
                             BigDecimal.valueOf(0.5),
                             BigDecimal.valueOf(0.25),
                             BigDecimal.valueOf(0.1),
                             BigDecimal.valueOf(0.05)
                ),

                TrocoService.defineMenorQuantidadeDeNotas(BigDecimal.valueOf(57.93))
        );
    }

}
