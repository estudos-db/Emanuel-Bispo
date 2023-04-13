package util;

import java.util.Random;

public class GeradorCodigoEmprestimo {
    public static String gera() {

        StringBuffer thebuffer = new StringBuffer();
        String chars = "0123456789";
        int codigoSize = 6;

        for (int i = 0; i < codigoSize; i++) {
            thebuffer.append(chars.charAt(new Random().nextInt(chars.length())));
        }
        return thebuffer.toString();

    }
}
