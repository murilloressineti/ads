public class Receita extends Transacao implements Tributavel {

    public Receita(String descricao, double valor, String data) throws ValorInvalidoException {
        super(descricao, valor, data);
    }

    @Override
    public double calcularImposto() {
        // Regra de negócio: 10% do valor
        return getValor() * 0.10;
    }

    @Override
    public void exibirDetalhes() {
        System.out.println("+++ DETALHE DE RECEITA +++");
        System.out.println("Data: " + getData());
        System.out.println("Descrição: " + getDescricao());
        System.out.println("Valor: R$ " + getValor());
        System.out.println("Imposto Estimado (10%): R$ " + calcularImposto());
        System.out.println("+++++++++++++++++++++++++++");
    }
}
