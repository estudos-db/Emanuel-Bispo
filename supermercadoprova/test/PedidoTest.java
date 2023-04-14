import model.Item;
import model.Pedido;
import model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PedidoTest {
    private Pedido pedido;

    @BeforeEach
    void setup() {
        Produto produtoCafe = new Produto(1, "Café", BigDecimal.valueOf(9.0), 3);
        Item item = new Item(produtoCafe, 4);
        pedido = new Pedido(new ArrayList<>(Arrays.asList(item)));
    }

    @Test
    void adicionaItemNaListaDeveAdicionarComSucesso() {
        assertEquals(1, pedido.getListaDeItens().size());

        Produto produtoBanana = new Produto(1, "Banana", BigDecimal.valueOf(15.0), 10);
        Item item1 = new Item(produtoBanana, 2);
        pedido.adicionaItemNaLista(item1);

        assertEquals(2, pedido.getListaDeItens().size());
    }

    @Test
    void limparCarrinhoDeveLimparOCarrinhoComSucesso() {
        assertEquals(1, pedido.getListaDeItens().size());
        pedido.limparCarrinho();
        assertEquals(0, pedido.getListaDeItens().size());
        assertEquals(BigDecimal.valueOf(0), pedido.getValorTotal());
    }

    @Test
    void calculaValorTotalDefinirCorretamenteOValorTotal() {
        pedido.calculaValorTotal();
        assertEquals(BigDecimal.valueOf(36.0), pedido.getValorTotal());
    }

}
