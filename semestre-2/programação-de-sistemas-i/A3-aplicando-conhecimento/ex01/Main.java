import java.util.Scanner;

// Início da seção com a definição da classe Celular ///////////////////////

class Celular {
  String modelo;
  String marca;
  long imei;
  String numero;
  String cor;
  int anoFabricacao;

  public String verificaGarantia() {
    int anoAtual = java.time.Year.now().getValue();
    if ((anoAtual - this.anoFabricacao) <= 2) {
      return "Dentro da garantia";
    } else {
      return "Fora da garantia";
    }
  }

  public boolean checaImei(long nro) {
    return this.imei == nro;
  }

  public String exibeNumeroFormatado() {
    if (this.numero.length() >= 11) {
      String ddd = this.numero.substring(0, 2);
      String primeiraParte = this.numero.substring(2, 7);
      String segundaParte = this.numero.substring(7, 11);
      return "(" + ddd + ") " + primeiraParte + "-" + segundaParte;
    } else {
      return this.numero;
    }
  }
}

// Fim da seção com a definição da classe Celular ///////////////////////

public class Main {
  public static void main(String[] args) {
    Scanner input = new Scanner(System.in);
    Celular c = new Celular();
    c.modelo = input.next();
    c.marca = input.next();
    c.imei = input.nextLong();
    c.numero = input.next();
    c.cor = input.next();
    c.anoFabricacao = input.nextInt();

    System.out.print(fichaTecnica(c) + "\n" + "Imei: " + ((c.checaImei(input.nextLong())) ? "válido" : "inválido"));
    input.close();
  }

  static String fichaTecnica(Celular c) {
    return "Modelo: " + c.modelo + "\n" +
        "Marca: " + c.marca + "\n" +
        "Imei: " + c.imei + "\n" +
        "Número: " + c.exibeNumeroFormatado() + "\n" +
        "Cor: " + c.cor + "\n" +
        "Ano de Fabricação: " + c.anoFabricacao + "\n" +
        "Garantia: " + c.verificaGarantia();

  }
}