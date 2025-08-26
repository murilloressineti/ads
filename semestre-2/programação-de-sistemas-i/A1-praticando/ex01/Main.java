import java.util.Scanner;

public class Main {
	public static void main(String[] args) {
		double n1, n2;
		int op;
		Scanner entrada = new Scanner(System.in);

		while (true) {
			op = entrada.nextInt();
			if (op == 5) // sai do while com a opção 5
				break;
			if (op < 1 || op > 6) { // não executa o trecho após o fim do bloco e volta ao while
				System.out.println("Opção do menu inválida");
				continue;
			}
			n1 = entrada.nextDouble();
			n2 = entrada.nextDouble();
			switch (op) {
				case 1:
					// Adição
					System.out.println("Soma = " + (n1 + n2));
					break;
				case 2:
					// Subtração
					System.out.println("Subtração = " + (n1 - n2));
					break;
				case 3:
					// Multiplicação
					System.out.println("Multiplicação = " + (n1 * n2));
					break;
				case 4:
					// Divisão
					System.out.println("Divisão = " + (n1 / n2));
					break;
				default:
					System.out.println("Opção inválida");
			}
		}

		entrada.close();
	}
}