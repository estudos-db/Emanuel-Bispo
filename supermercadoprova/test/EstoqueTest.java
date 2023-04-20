import model.Estoque;
import model.Produto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class EstoqueTest {

    private Estoque estoque;

    @BeforeEach
    void setup() {
        ArrayList<Produto> produtos = new ArrayList<>(
                List.of(
                        Constants.CAFE,
                        Constants.CEREAL,
                        Constants.FEIJAO
                )
        );
        estoque = new Estoque(produtos);
    }

    @Test
    void getQuantidadeProdutoNoEstoqueDeveRetornarQuantidadeComSucesso() {
        assertEquals(7, estoque.getQuantidadeProdutoNoEstoque("cereal"));
    }

    @Test
    void getQuantidadeProdutoNoEstoqueDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class, () -> estoque.getQuantidadeProdutoNoEstoque("produto-invalido"));
    }

    @Test
    void buscaProdutoPorNomeDeveRetornarProdutoComSucesso() {
        assertEquals(Constants.CAFE, estoque.buscaProdutoPorNome("café"));
    }

    @Test
    void buscaProdutoPorNomeDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class, () -> estoque.buscaProdutoPorNome("produto-invalido"));
    }

    @Test
    void buscaProdutoPorIdDeveRetornarProdutoComSucesso() {
        assertEquals(Constants.FEIJAO, estoque.buscaProdutoPorId(3));
    }

    @Test
    void buscaProdutoPorIdDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class, () -> estoque.buscaProdutoPorId(100));
    }

    @Test
    void cadastraProdutoDeveCadastrarProduto() {
        assertEquals(3, estoque.getTamanhoVariedadeProdutosNoEstoque());
        estoque.cadastraProduto("arroz", BigDecimal.valueOf(5), 10);
        assertEquals(4, estoque.getTamanhoVariedadeProdutosNoEstoque());
    }

    @Test
    void darBaixaPorNomeDeveRemoverQuantidadeProdutoCorretaDoEstoque() {
        assertEquals(2, Constants.CAFE.getQuantidadeEmEstoque());
        estoque.darBaixaPorNome("café", 1);
        assertEquals(1, Constants.CAFE.getQuantidadeEmEstoque());
    }

    @Test
    void darBaixaPorNomeDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class,() -> estoque.darBaixaPorNome("produto-invalido", 1));
    }

    @Test
    void darBaixaPorIdDeveRemoverQuantidadeProdutoCorretaDoEstoque() {
        assertEquals(5, Constants.FEIJAO.getQuantidadeEmEstoque());
        estoque.darBaixaPorId(3, 5);
        assertEquals(0, Constants.FEIJAO.getQuantidadeEmEstoque());
    }

    @Test
    void darBaixaPorIdDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class, () -> estoque.darBaixaPorId(100, 1));
    }

    @Test
    void temEstoqueDeveRetornarTrueParaProdutoComEstoque() {
        assertTrue(estoque.temEstoque("cereal"));
        assertTrue(estoque.temEstoque("café"));
    }

    @Test
    void temEstoqueDeveRetornarFalseParaProdutoSemEstoque() {
        assertTrue(estoque.temEstoque("feijão"));
    }

    @Test
    void temEstoqueDeveLancarExcecaoParaProdutoInvalido() {
        assertThrows(RuntimeException.class, () -> estoque.temEstoque("produto-invalido"));
    }

}
