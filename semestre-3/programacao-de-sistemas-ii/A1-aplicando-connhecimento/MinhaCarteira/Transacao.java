public abstract class Transacao {
    private String descricao;
    private double valor;
    private String data;

    // Construtor para inicializar os atributos
    public Transacao(String descricao, double valor, String data) {
        this.descricao = descricao;
        this.valor = valor;
        this.data = data;
    }

    // Getters e Setters (Encapsulamento)
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }

    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }

    public String getData() { return data; }
    public void setData(String data) { this.data = data; }

    // Método que as classes filhas serão obrigadas a implementar
    public abstract void exibirDetalhes();
}