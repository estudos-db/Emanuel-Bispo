import model.Produto;

import java.math.BigDecimal;

public class Constants {
    public static Produto CAFE = new Produto(1, "café", BigDecimal.valueOf(9.50), 2);
    public static Produto CEREAL = new Produto(2, "cereal", BigDecimal.valueOf(12), 7);
    public static Produto FEIJAO = new Produto(3, "feijão", BigDecimal.valueOf(10), 5);
}
