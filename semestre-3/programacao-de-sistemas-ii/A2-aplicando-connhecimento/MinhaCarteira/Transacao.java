public abstract class Transacao {
    private String descricao;
    private double valor;
    private String data;

    // O construtor agora avisa que pode lançar um erro de valor inválido
    public Transacao(String descricao, double valor, String data) throws ValorInvalidoException {
        
        // Validação da Descrição (Usando exceção nativa do Java)
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição não pode ser vazia.");
        }

        // Validação do Valor (Usando sua exceção personalizada)
        if (valor <= 0) {
            throw new ValorInvalidoException("O valor da transação deve ser maior que zero.");
        }

        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters
    public String getDescricao() { return descricao; }
    public double getValor() { return valor; }
    public String getData() { return data; }

    public abstract void exibirDetalhes();
}