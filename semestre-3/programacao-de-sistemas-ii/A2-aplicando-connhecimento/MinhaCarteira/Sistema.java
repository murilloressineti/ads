import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        List<Transacao> minhasTransacoes = new ArrayList<>();

        System.out.println("--- Iniciando Cadastro de Transações ---\n");

        // Teste 1: Cadastro Válido
        try {
            minhasTransacoes.add(new Receita("Salário", 5000.0, "05/03/2026"));
            System.out.println("Sucesso: Receita adicionada.");
        } catch (ValorInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // Teste 2: Cadastro Inválido (Valor negativo)
        try {
            minhasTransacoes.add(new Despesa("Multa", -100.0, "10/03/2026"));
            System.out.println("Sucesso: Despesa adicionada.");
        } catch (ValorInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        // Teste 3: Cadastro Inválido (Descrição vazia)
        try {
            minhasTransacoes.add(new Receita("", 200.0, "11/03/2026"));
        } catch (ValorInvalidoException | IllegalArgumentException e) {
            System.out.println("Erro capturado: " + e.getMessage());
        }

        System.out.println("\n--- Resumo das Transações Válidas ---");
        for (Transacao t : minhasTransacoes) {
            t.exibirDetalhes();
        }
    }
}