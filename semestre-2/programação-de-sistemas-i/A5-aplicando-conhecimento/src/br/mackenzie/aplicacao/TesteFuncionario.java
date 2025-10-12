// Pacote br.mackenzie.aplicacao [cite: 26]
package br.mackenzie.aplicacao;

// Importa a classe Funcionario do outro pacote [cite: 16]
import br.mackenzie.modelo.Funcionario;

public class TesteFuncionario {

    public static void main(String[] args) {
        // ----------------------
        // 1. Instanciar e usar métodos públicos [cite: 27]
        // ----------------------
        Funcionario func = new Funcionario("Maria Silva", 5000.00);

        // Usando o getter público
        System.out.println("Funcionário: " + func.getNome());
        System.out.println("Salário Atual: R$ " + func.getSalario());

        // Usando o setter público para modificar o salário
        func.setSalario(6500.00);
        System.out.println("Novo Salário (via setter): R$ " + func.getSalario());
        
        System.out.println("----------------------------------------");


        // ----------------------
        // 2. Tentar acessar membros restritos (e comentar) [cite: 28, 29, 34]
        // ----------------------

        // Tente acessar os atributos privados diretamente [cite: 32]
        /*
        // O acesso NÃO é permitido.
        // Motivo: Os atributos 'nome' e 'salario' são declarados como 'private'
        // na classe Funcionario, o que restringe o acesso apenas a essa classe. [cite: 17, 34]
        func.nome = "Tentativa de alteração"; 
        System.out.println(func.salario);
        */

        // Tente acessar o método protegido calcularBonus() [cite: 33]
        /*
        // O acesso NÃO é permitido.
        // Motivo: O método é 'protected'. O acesso protected é permitido apenas 
        // dentro do mesmo pacote (br.mackenzie.modelo) ou por classes filhas.
        // Como TesteFuncionario está em um pacote diferente (br.mackenzie.aplicacao)
        // e não herda de Funcionario, ele não pode acessá-lo. [cite: 23, 34]
        double bonus = func.calcularBonus();
        System.out.println("Bônus: " + bonus); 
        */

        // Tente acessar o método default exibirDetalhes() [cite: 33]
        /*
        // O acesso NÃO é permitido.
        // Motivo: O método tem acesso 'default' (sem modificador).
        // O acesso default é permitido apenas para classes que estão no MESMO PACOTE.
        // Como esta classe está no pacote 'br.mackenzie.aplicacao' e a classe
        // Funcionario está em 'br.mackenzie.modelo', o acesso é negado. [cite: 25, 34]
        func.exibirDetalhes(); 
        */

        System.out.println("Fim da Demonstração.");

    }
}