package service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class TrocoService {
    public static BigDecimal calculaTrocoEmDinheiro(BigDecimal valorFinal, BigDecimal valorPago) {
        return valorPago.subtract(valorFinal);
    }

    public static List<BigDecimal> defineMenorQuantidadeDeNotas(BigDecimal troco) {
        List<BigDecimal> notas = List.of(
                BigDecimal.valueOf(200), BigDecimal.valueOf(100),
                BigDecimal.valueOf(50), BigDecimal.valueOf(20), BigDecimal.valueOf(10),
                BigDecimal.valueOf(5), BigDecimal.valueOf(2),
                BigDecimal.valueOf(1), BigDecimal.valueOf(0.5),
                BigDecimal.valueOf(0.25), BigDecimal.valueOf(0.10),
                BigDecimal.valueOf(0.05),
                BigDecimal.valueOf(0.01)
        );

        List<BigDecimal> notasDoTroco = new ArrayList<>();

        var contagemValorAtual = BigDecimal.ZERO;

        while (contagemValorAtual.compareTo(troco) < 0) {
            var restante = troco.subtract(contagemValorAtual);
            for (BigDecimal nota : notas) {
                if(nota.compareTo(restante) <= 0 ) {
                    notasDoTroco.add(nota);
                    contagemValorAtual =  contagemValorAtual.add(nota);
                    break;
                }
            }
        }

        return notasDoTroco;
    }
}
