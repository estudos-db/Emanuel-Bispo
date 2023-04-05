package util;

public class Validacao {
    public static boolean isEspacoGuincho(int pontosEspacoTotal, int pontosEspacoAtual, int pontosEspacoVeiculo) {
        if(pontosEspacoTotal - pontosEspacoAtual < pontosEspacoVeiculo)
                throw new RuntimeException("Não há espaço para mais veículo");

        return true;
    }
}
