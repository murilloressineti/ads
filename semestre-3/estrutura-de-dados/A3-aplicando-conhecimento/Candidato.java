// Classe Candidato contendo dados de candidatos 
// a um concurso musical.
// Autor: Ivan Carlos Alcântara de Oliveira.

public class Candidato {
    // Atributos privados
    private int numero_candidato;
    private String cpf;
    private String nome;
    private int idade;
    private char sexo;
    private String genero_musical;
    
    // Construtor sem parâmetros (Default)
    public Candidato() {
        this(0, "000.000.000-00", "-", 0, '-', "-");
    }
    
    // Construtor com todos os parâmetros
    public Candidato(int numero_candidato, String cpf, String nome, int idade, char sexo, String genero_musical) {
        this.numero_candidato = numero_candidato;
        this.cpf = cpf;
        this.nome = nome;
        this.idade = idade;
        this.sexo = sexo;
        this.genero_musical = genero_musical;
    }

    // --- MÉTODOS GETTERS E SETTERS ---
    // Essenciais para os algoritmos de ordenação acessarem os dados

    public int getNumero_candidato() {
        return numero_candidato;
    }

    public void setNumero_candidato(int numero_candidato) {
        this.numero_candidato = numero_candidato;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getIdade() {
        return idade;
    }

    public void setIdade(int idade) {
        this.idade = idade;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public String getGenero_musical() {
        return genero_musical;
    }

    public void setGenero_musical(String genero_musical) {
        this.genero_musical = genero_musical;
    }

    // Sobrescrita do método toString()
    // Utilizado para exibir os dados do candidato de forma legível no console
    @Override
    public String toString() {
        return String.format("Nº: %-5d | Nome: %-15s | Idade: %-3d | Gênero: %-10s | CPF: %s", 
                numero_candidato, nome, idade, genero_musical, cpf);
    }
}