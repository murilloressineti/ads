import classes.aluno.Aluno;
import classes.curso.Curso;
import classes.departamento.Departamento;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Teste de Associações (Composição e Agregação) ---");

        // 1. Criação e associação das classes
        Aluno aluno1 = new Aluno("Joana Silva", "TADS2025");
        
        // 2. Cria o Curso (lado independente da Agregação) e o associa ao Aluno
        Curso cursoTADS = new Curso("Tecnologia em Análise e Desenv. de Sistemas", "C001");
        cursoTADS.setAluno(aluno1);
        
        // 3. Cria o Departamento (lado principal da Composição) e o associa ao Curso
        Departamento dptoComputacao = new Departamento("Departamento de Ciência da Computação", cursoTADS);
        
        // 4. Impressão final de todos os dados através da classe principal (Departamento)
        System.out.println("\n--- Impressão dos Dados ---");
        System.out.println(dptoComputacao.toString());
        
        // Teste de Agregação: Aluno existe independentemente do Curso.
        System.out.println("\n--- Teste de Independência do Aluno (Agregação) ---");
        System.out.println("Aluno original ainda existe: " + aluno1.getNome() + " / Matrícula: " + aluno1.getMatricula());
    }
}