import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GerenciadorArquivos {

    private static final String ARQUIVO_BIN = "transacoes.bin";
    private static final String ARQUIVO_CSV = "extrato.csv";

    // Salva a lista completa em formato binário (para o sistema ler depois)
    public static void salvarDados(List<Transacao> transacoes) {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ARQUIVO_BIN))) {
            oos.writeObject(transacoes);
            System.out.println("Dados salvos com sucesso em " + ARQUIVO_BIN);
        } catch (IOException e) {
            System.out.println("Erro ao salvar dados: " + e.getMessage());
        }
    }

    // Carrega a lista do arquivo binário
    public static List<Transacao> carregarDados() {
        File arquivo = new File(ARQUIVO_BIN);
        if (!arquivo.exists()) {
            return new ArrayList<>(); // Se não existe arquivo, retorna lista vazia
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ARQUIVO_BIN))) {
            return (List<Transacao>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Erro ao carregar dados: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    // Cria o arquivo de texto para abrir no Excel/Planilhas
    public static void gerarExtrato(List<Transacao> transacoes) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARQUIVO_CSV))) {
            writer.write("Descricao;Valor;Data\n"); // Cabeçalho do CSV
            for (Transacao t : transacoes) {
                String linha = t.getDescricao() + ";" + t.getValor() + ";" + t.getData();
                writer.write(linha + "\n");
            }
            System.out.println("Extrato exportado com sucesso para " + ARQUIVO_CSV);
        } catch (IOException e) {
            System.out.println("Erro ao gerar extrato: " + e.getMessage());
        }
    }
}