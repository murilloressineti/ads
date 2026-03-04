import java.util.ArrayList;
import java.util.List;

public class CadastroProdutos {
    private List<Produto> produtos = new ArrayList<>();

    public void adicionarProduto(String nome, double preco, int quantidade) {
        try {
            // Tenta criar o produto. Se as regras do arquivo Produto.java falharem, pula pro 'catch'
            Produto novo = new Produto(nome, preco, quantidade);
            produtos.add(novo);
            System.out.println("SUCESSO: " + novo);
        } catch (NomeInvalidoException | PrecoInvalidoException | QuantidadeInvalidaException e) {
            // Mostra a mensagem de erro que definimos lá no Produto.java
            System.out.println("ERRO AO CADASTRAR: " + e.getMessage());
        }
    }

    public void listarProdutos() {
        System.out.println("\n--- PRODUTOS NO ESTOQUE ---");
        for (Produto p : produtos) {
            System.out.println(p);
        }
    }
}