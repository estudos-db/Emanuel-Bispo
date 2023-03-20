import java.util.Scanner;

public class MatrizQuadrada {

    private Scanner sc;
    private int[][] matriz;

    public MatrizQuadrada() {
        this.matriz = new int[4][4];
        this.sc = new Scanner(System.in);
    }

    public int[][] montaMatriz() {
        for (int i = 0; i < matriz.length; i++){
            for (int j = 0; j < matriz[i].length; j++){
                System.out.println("Digite um numero inteiro para LINHA: "+(i+1)+" COLUNA: "+(j+1));
                matriz[i][j] = sc.nextInt();
            }
        }
        return matriz;
    }

    public int somaDiagonalPrincipal(int[][] matriz) {
        int soma = 0;

        for (int i = 0; i < matriz.length; i++)
            for (int j = 0; j < matriz[i].length; j++)
                if(i == j) soma += matriz[i][j];

        return soma;
    }

    public int[][] getMatriz() {
        return matriz;
    }

}
