package br.tec.rental.library.domain.service.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class FormatadorData {
    public static Date execute(String formatadoDataAntigo, String formatadoDataNovo, String data) {
        SimpleDateFormat dataFormatada = new SimpleDateFormat(formatadoDataAntigo);
        Date novaData;

        try {
            novaData = dataFormatada.parse(data);
            dataFormatada.applyPattern(formatadoDataNovo);
        }catch (ParseException e) {
            throw new RuntimeException("Erro ao converter data");
        }

        return new Date(dataFormatada.format(novaData));
    }
}
