public class Produto {
    private String nome;
    private double preco;
    private int quantidade;

    public Produto(String nome, double preco, int quantidade) 
            throws NomeInvalidoException, PrecoInvalidoException, QuantidadeInvalidaException {

        // Regra 1: Nome não pode ser vazio
        if (nome == null || nome.trim().isEmpty()) {
            throw new NomeInvalidoException("O nome não pode ser vazio.");
        }
        // Regra 2: Preço deve ser positivo
        if (preco <= 0) {
            throw new PrecoInvalidoException("O preço deve ser maior que zero.");
        }
        // Regra 3: Quantidade não pode ser negativa
        if (quantidade < 0) {
            throw new QuantidadeInvalidaException("A quantidade não pode ser negativa.");
        }

        this.nome = nome;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    @Override
    public String toString() {
        return "Produto: " + nome + " | Preço: R$" + preco + " | Quantidade: " + quantidade;
    }
}