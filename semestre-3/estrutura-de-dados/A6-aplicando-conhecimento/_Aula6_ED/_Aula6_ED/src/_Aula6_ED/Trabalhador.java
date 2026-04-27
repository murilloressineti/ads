package _Aula6_ED;

public class Trabalhador {
    private int id;
    private int idade;
    private char sexo;
    private float salario;
    private String cargo;

    public Trabalhador(int id, int idade, char sexo, float salario, String cargo) {
        this.id = id;
        this.idade = idade;
        this.sexo = sexo;
        this.salario = salario;
        this.cargo = cargo;
    }

    public int getId() { return id; }
    public String getCargo() { return cargo; }
    public float getSalario() { return salario; }
    public void setSalario(float salario) { this.salario = salario; }

    @Override
    public String toString() {
        return "\n[Trabalhador] Id: " + id + ", sexo: " + sexo + ", idade: " + idade + ", cargo: " + cargo + ", salário: " + salario;
    }
}