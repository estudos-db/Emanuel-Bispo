public class Tabuada {
    public void contar(int numero) {

        int multiplicador = 1;

        for(int i = 1; i <= 10; i++){
            System.out.println(numero + "x" +  multiplicador + " = " + numero * multiplicador++);
        }

    }
}
