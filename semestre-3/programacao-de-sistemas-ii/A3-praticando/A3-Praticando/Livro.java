import java.io.Serializable;

public class Livro implements Serializable {
    private static final long serialVersionUID = 1L;
    private String titulo;
    private String autor;
    private int anoPublicacao;
    private transient double preco; // Não será serializado

    public Livro(String titulo, String autor, int anoPublicacao, double preco) {
        this.titulo = titulo;
        this.autor = autor;
        this.anoPublicacao = anoPublicacao;
        this.preco = preco;
    }

    @Override
    public String toString() {
        return "Livro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", anoPublicacao=" + anoPublicacao +
                ", preco=" + preco + 
                '}';
    }
}