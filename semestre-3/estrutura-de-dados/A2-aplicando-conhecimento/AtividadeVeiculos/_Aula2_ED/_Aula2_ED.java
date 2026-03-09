package _Aula2_ED;

import java.util.ArrayList;
import java.util.Scanner;

public class _Aula2_ED {
  
    private ArrayList veic = new ArrayList(); 
    private Scanner ler = new Scanner(System.in); 

    public static void main(String[] args) {
        _Aula2_ED loja = new _Aula2_ED();
        System.exit(0);
    }
      
    public _Aula2_ED() {
        simularLeituraDeDados();
        System.out.println("Resultados: \n");
        mediaPrecosCaminhoesVendidos();
        veiculoMaisCaroMaisBarato();
        mediaPrecosDeUmTipoDeVeiculo();
        listarTodosOsVeiculosDaLoja();
        contarVeiculosPorTipo();
    }
      
    public void simularLeituraDeDados() {
        veic.add(new Veiculo("ABC-1L34","Honda","GL 1800", 2023, 150000f,"moto")); 
        veic.add(new Veiculo("DEF-2K45","Volvo","Scania X", 2015, 179000f,"caminhão")); 
        veic.add(new Veiculo("EDF-1P34","Audi","A4", 2019, 234000f,"carro")); 
        veic.add(new Veiculo("FGE-2A45","Ford","Focus", 2018, 89000f,"carro")); 
        veic.add(new Veiculo("ABD-1B34","Fiat","Track Gls", 2022, 112000f,"carro")); 
        veic.add(new Veiculo("DGF-2C45","Volvo","Scania X", 2024, 220000f,"caminhão")); 
        veic.add(new Veiculo("FGP-2M45","Ford","Focus", 2021, 120000f,"carro")); 
        veic.add(new Veiculo("ABC-1234","Honda","GL 1000", 2024, 140000f,"moto")); 
        veic.add(new Veiculo("AEF-2Q45","Volvo","Scania X", 2019, 195000f,"caminhão")); 
        veic.add(new Veiculo("IGN-1W45","Ford","Focus", 2020, 110000f,"carro")); 
    }

    public void mediaPrecosCaminhoesVendidos() {
        float soma = 0;
        int cont = 0;
        for (int i = 0; i < veic.size(); i++) {
            Veiculo v = (Veiculo) veic.get(i);
            if (v.getTipo().equalsIgnoreCase("caminhão")) {
                soma += v.getPreco();
                cont++;
            }
        }
        if (cont > 0) {
            System.out.println("Médias dos preços dos caminhões: R$ " + String.format("%.2f", (soma / cont)));
        } else {
            System.out.println("Não existem caminhões na loja");
        }
    }

    public void veiculoMaisCaroMaisBarato() {
        if (veic.isEmpty()) {
            System.out.println("Não há veículos cadastrados!");
            return;
        }
        Veiculo caro = (Veiculo) veic.get(0);
        Veiculo barato = (Veiculo) veic.get(0);
        for (int i = 1; i < veic.size(); i++) {
            Veiculo atual = (Veiculo) veic.get(i);
            if (atual.getPreco() > caro.getPreco()) caro = atual;
            if (atual.getPreco() < barato.getPreco()) barato = atual;
        }
        System.out.println("Mais caro: " + caro.toString());
        System.out.println("Mais barato: " + barato.toString());
    }

    public void mediaPrecosDeUmTipoDeVeiculo() {
        System.out.print("\nDigite o tipo de veículo (carro, caminhão ou moto): ");
        String tipo = ler.next();
        float soma = 0;
        int cont = 0;
        for (int i = 0; i < veic.size(); i++) {
            Veiculo v = (Veiculo) veic.get(i);
            if (v.getTipo().equalsIgnoreCase(tipo)) {
                soma += v.getPreco();
                cont++;
            }
        }
        if (cont > 0) {
            System.out.println("Médias dos preços dos veículos do tipo " + tipo + ": R$ " + String.format("%.2f", (soma / cont)));
        } else {
            System.out.println("Não existem veículos desse tipo na loja");
        }
    }

    public void listarTodosOsVeiculosDaLoja() {
        if (veic.isEmpty()) {
            System.out.println("Não há veículos cadastrados!");
        } else {
            System.out.println("\nVeículos cadastrados:");
            for (int i = 0; i < veic.size(); i++) {
                System.out.println(veic.get(i).toString());
            }
        }
    }

    public void contarVeiculosPorTipo() {
        int c = 0, cam = 0, m = 0;
        for (int i = 0; i < veic.size(); i++) {
            Veiculo v = (Veiculo) veic.get(i);
            if (v.getTipo().equalsIgnoreCase("carro")) c++;
            else if (v.getTipo().equalsIgnoreCase("caminhão")) cam++;
            else if (v.getTipo().equalsIgnoreCase("moto")) m++;
        }
        System.out.println("\nContagem por tipo:");
        System.out.println("Carros: " + c + " | Caminhões: " + cam + " | Motos: " + m);
    }
}