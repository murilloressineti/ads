package classes.curso;

import classes.aluno.Aluno;

public class Curso {
    private String nome;
    private String codigo;
    private Aluno aluno; // Agregação (Curso-Aluno)

    public Curso() {
    }

    public Curso(String nome, String codigo) {
        this.nome = nome;
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Aluno getAluno() {
        return aluno;
    }

    public void setAluno(Aluno aluno) {
        this.aluno = aluno;
    }

    @Override
    public String toString() {
        String dadosCurso = "Curso: " + nome + " (Código: " + codigo + ")";
        
        if (aluno != null) {
            dadosCurso += "\n" + aluno.toString();
        } else {
            dadosCurso += "\n  -> Nenhum aluno associado (Agregação)";
        }
        return dadosCurso;
    }
}