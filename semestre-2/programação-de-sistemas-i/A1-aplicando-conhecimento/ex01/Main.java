import java.util.Scanner;
import java.lang.Math;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int op;
        double n1, n2;

        while (true) {
            op = entrada.nextInt();

            if (op == 6) {
                break;
            }
            
            if (op < 1 || op > 6) {
                System.out.println("OPÇÃO INVÁLIDA");
                continue;
            }
            
            n1 = entrada.nextDouble();
            n2 = entrada.nextDouble();
            
            switch (op) {
                case 1:
                    System.out.println("Potência = " + Math.pow(n1, n2));
                    break;
                case 2:
                    System.out.println("Raiz Quadrada de " + n1 + " = " + Math.sqrt(n1));
                    System.out.println("Raiz Quadrada de " + n2 + " = " + Math.sqrt(n2));
                    break;
                case 3:
                    System.out.println("Máximo = " + Math.max(n1, n2));
                    break;
                case 4:
                    System.out.println("Mínimo = " + Math.min(n1, n2));
                    break;
                case 5:
                    System.out.println("Média Geométrica = " + Math.sqrt(n1 * n2));
                    break;
            }
        }
        entrada.close();
    }
}