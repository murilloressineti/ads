import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int n = entrada.nextInt();
        long fatorial = 1;

        if (n < 0) {
            System.out.println("Numero negativo nao possui fatorial definido");
        } else if (n == 0) {
            System.out.println("Fatorial de 0: 0! = 1");
        } else {
            System.out.print("Fatorial de " + n + ": " + n + "! = ");
            String sequencia = "";
            for (int i = n; i >= 1; i--) {
                fatorial *= i;
                sequencia += i;
                if (i > 1) {
                    sequencia += " x ";
                }
            }
            System.out.println(sequencia + " = " + fatorial);
        }

        entrada.close();
    }
}
