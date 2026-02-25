public class Despesa extends Transacao {

    public Despesa(String descricao, double valor, String data) {
        super(descricao, valor, data);
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("--- DETALHE DE DESPESA ---");
        System.out.println("Data: " + getData());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Valor: -R$ " + getValor());
        System.out.println("--------------------------");
    }
}