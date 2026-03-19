import java.util.List;

public class Sistema {
    public static void main(String[] args) {
        // 1. Início: Tenta carregar os dados salvos
        List<Transacao> minhasTransacoes = GerenciadorArquivos.carregarDados();
        System.out.println("Total de transacoes carregadas: " + minhasTransacoes.size());

        // 2. Inserção: Adicionamos uma nova para testar se a lista cresce
        try {
            // Criando uma transação nova a cada vez que você rodar o programa
            String desc = "Compra Numero " + (minhasTransacoes.size() + 1);
            minhasTransacoes.add(new Despesa(desc, 50.0, "11/03/2026"));
            System.out.println("Nova transacao adicionada: " + desc);
        } catch (ValorInvalidoException e) {
            System.out.println("Erro: " + e.getMessage());
        }

        // 3. Salvar: Grava a lista atualizada no arquivo binário
        GerenciadorArquivos.salvarDados(minhasTransacoes);

        // 4. Exportar: Gera o arquivo de texto para o usuário
        GerenciadorArquivos.gerarExtrato(minhasTransacoes);

        // Exibe o resumo no terminal
        System.out.println("\n--- Resumo Atual ---");
        for (Transacao t : minhasTransacoes) {
            t.exibirDetalhes();
        }
    }
}