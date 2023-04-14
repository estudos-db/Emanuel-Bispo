import model.Item;
import model.Produto;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ItemTest {
    @Test
    void deveRetornarOValorTotalComSucesso() {
        Produto produtoCafe = new Produto(1, "Café", BigDecimal.valueOf(9.0), 3);
        Item item = new Item(produtoCafe, 2);

        assertEquals(BigDecimal.valueOf(18.0), item.getValorDoItem());
    }
}
