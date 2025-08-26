import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner entrada = new Scanner(System.in);

    int n = Integer.parseInt(entrada.nextLine());
    Pedido[] pedidos = new Pedido[n];
    double totalGeral = 0;

    for (int i = 0; i < n; i++) {
      int id = Integer.parseInt(entrada.nextLine());
      String nome = entrada.nextLine();
      int quantidade = Integer.parseInt(entrada.nextLine());
      double preco = Double.parseDouble(entrada.nextLine());

      pedidos[i] = new Pedido(id, nome, quantidade, preco);
    }

    System.out.println("Resumo dos Pedidos:");
    for (int i = 0; i < n; i++) {
      pedidos[i].exibirDetalhes();
      totalGeral += pedidos[i].calcularTotal();
    }

    System.out.println("Total Geral: " + totalGeral);

    entrada.close();
  }
}

class Pedido {
  int id;
  String nomeProduto;
  int quantidade;
  double precoUnitario;

  public Pedido(int id, String nomeProduto, int quantidade, double precoUnitario) {
    this.id = id;
    this.nomeProduto = nomeProduto;
    this.quantidade = quantidade;
    this.precoUnitario = precoUnitario;
  }

  public double calcularTotal() {
    return (double) this.quantidade * this.precoUnitario;
  }

  public void exibirDetalhes() {
    System.out.println("Pedido #" + this.id);
    System.out.println("Produto: " + this.nomeProduto);
    System.out.println("Quantidade: " + this.quantidade);
    System.out.println("Preço Unitário: " + this.precoUnitario);
    System.out.println("Total: " + this.calcularTotal());
    System.out.println("=====================");
  }
}