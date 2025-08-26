import java.util.Scanner;

public class Main {
  private static final int TAMANHO = 10;
  private static int[] vetor = new int[TAMANHO];
  private static int count = 0;

  // insira aqui o método void adicionarElemento(int elemento)
  public static void adicionarElemento(int elemento) {
    if (count < TAMANHO) {
      vetor[count] = elemento;
      count++;
    }
  }

  // insira aqui o método int calcularSoma()
  public static int calcularSoma() {
    int soma = 0;
    for (int i = 0; i < TAMANHO; i++) {
      soma += vetor[i];
    }
    return soma;
  }

  // insira aqui o método int encontrarMaior()
  public static int encontrarMaior() {
    int maior = vetor[0];
    for (int i = 1; i < TAMANHO; i++) {
      if (vetor[i] > maior) {
        maior = vetor[i];
      }
    }
    return maior;
  }

  // insira aqui o método int encontrarMenor()
  public static int encontrarMenor() {
    int menor = vetor[0];
    for (int i = 1; i < TAMANHO; i++) {
      if (vetor[i] < menor) {
        menor = vetor[i];
      }
    }
    return menor;
  }

  // insira aqui o método double calcularMedia(int soma)
  public static double calcularMedia(int soma) {
    return (double) soma / TAMANHO;
  }

  // NÃO ALTERE O CÓDIGO DO MÉTODO PRINCIPAL //
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);

    // Inserir elementos no vetor
    for (int i = 0; i < TAMANHO; i++) {
      int elemento = input.nextInt();
      adicionarElemento(elemento);
    }

    // Calcular e exibir soma dos elementos
    int soma = calcularSoma();
    System.out.println("Soma dos elementos: " + soma);

    // Encontrar e exibir o maior valor
    int maior = encontrarMaior();
    System.out.println("Maior valor: " + maior);

    // Encontrar e exibir o menor valor
    int menor = encontrarMenor();
    System.out.println("Menor valor: " + menor);

    // Calcular e exibir a média dos elementos
    double media = calcularMedia(soma);
    System.out.println("Média dos elementos: " + media);
    input.close();
  }
}