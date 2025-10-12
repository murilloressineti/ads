// Pacote br.mackenzie.modelo [cite: 16]
package br.mackenzie.modelo;

public class Funcionario {

    // ----------------------
    // Atributos Privados [cite: 17]
    // O acesso é restrito à própria classe.
    // ----------------------
    private String nome;   // [cite: 18]
    private double salario; // [cite: 19]

    // ----------------------
    // Construtor Público [cite: 20]
    // Inicializa os atributos nome e salario.
    // ----------------------
    public Funcionario(String nome, double salario) {
        this.nome = nome;
        this.salario = salario;
    }

    // ----------------------
    // Métodos Públicos (Getters e Setters) [cite: 21]
    // Permitem o controle de acesso aos atributos privados.
    // ----------------------
    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }

    // ----------------------
    // Método Protegido: calcularBonus() [cite: 22]
    // Acesso: Apenas dentro do mesmo pacote (br.mackenzie.modelo)
    // ou por classes filhas (herança). [cite: 23]
    // ----------------------
    protected double calcularBonus() {
        return this.salario * 0.10; // 10% do salário [cite: 22]
    }

    // ----------------------
    // Método Default (sem modificador): exibirDetalhes() [cite: 24]
    // Acesso: Apenas para classes do mesmo pacote (br.mackenzie.modelo). [cite: 25]
    // ----------------------
    void exibirDetalhes() {
        System.out.println("Nome: " + this.nome);
        System.out.println("Salário: R$ " + this.salario);
        // Observação: Este método consegue acessar o método protegido
        // porque está no mesmo pacote.
        System.out.println("Bônus (10%): R$ " + calcularBonus());
    }
}