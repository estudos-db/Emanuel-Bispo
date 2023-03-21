import java.util.ArrayList;
import java.util.List;

public class NotaAluno {

    private List<Double> notas;

    public NotaAluno() {
        this.notas = new ArrayList<>();
    }

    public Double calculaMedia(List<Double> notas) {
        Double nota = 0.0;

        for (int i = 0; i < notas.size(); i++)
            nota += notas.get(i);

        return Math.floor((nota / notas.size()) * 100) / 100;
    }

    public String situacao(Double media) {
        if(media > 6.0) {
            return "Aprovado";
        } else if (media >= 4 && media <= 6) {
            return "Verificação Suplementar";
        }else {
            return "Reprovado";
        }
    }

}
