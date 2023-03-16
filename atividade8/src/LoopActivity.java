import java.util.Scanner;

public class LoopActivity {
    private Integer numero;
    public void iniciar() {
        do{
            Scanner sc = new Scanner(System.in);
            System.out.println("Digite um numero: ");
            numero = sc.nextInt();
            System.out.println(numero);
        } while (numero != 10);
    }
}
