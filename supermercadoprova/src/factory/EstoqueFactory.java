package factory;

import model.Produto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class EstoqueFactory {
    public static ArrayList<Produto> get() {
        return new ArrayList<>(List.of(
                new Produto(1, "Arroz", BigDecimal.valueOf(4.5), 15),
                new Produto(2, "Feijão", BigDecimal.valueOf(9.99), 20),
                new Produto(3, "Macarrão", BigDecimal.valueOf(2.35), 20),
                new Produto(4, "Açucar", BigDecimal.valueOf(2.80), 10),
                new Produto(5, "Farinha", BigDecimal.valueOf(3.0), 10)
        ));
    }
}
