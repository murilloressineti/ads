import java.util.ArrayList;
import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        // Lista polimórfica: aceita qualquer objeto que seja uma Transacao
        List<Transacao> minhasTransacoes = new ArrayList<>();

        // Instanciando os objetos conforme solicitado
        minhasTransacoes.add(new Despesa("Conta de Luz", 150.00, "10/02/2026"));
        minhasTransacoes.add(new Receita("Salário Mensal", 5000.00, "05/02/2026"));
        minhasTransacoes.add(new Despesa("Supermercado", 600.00, "12/02/2026"));

        // Loop para percorrer a lista
        for (Transacao t : minhasTransacoes) {
            t.exibirDetalhes(); // Polimorfismo: chama o método da classe correta

            // Verificação de instância para imposto
            if (t instanceof Tributavel) {
                // Cast (conversão temporária) para acessar o método da interface
                Tributavel objTributavel = (Tributavel) t;
                System.out.println("LOG: Processando tributação de " + t.getDescricao() + 
                                   " | Imposto: R$ " + objTributavel.calcularImposto());
            }
            System.out.println(); // Pular linha para organizar
        }
    }
}