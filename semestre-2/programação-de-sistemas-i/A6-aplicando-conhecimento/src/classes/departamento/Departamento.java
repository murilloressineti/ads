package classes.departamento;

import classes.curso.Curso;

public class Departamento {
    private String nome;
    private Curso curso; // Composição (Departamento-Curso)

    public Departamento() {
    }

    public Departamento(String nomeDpto, Curso curso) {
        this.nome = nomeDpto;
        this.curso = curso; 
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Curso getCurso() {
        return curso;
    }

    @Override
    public String toString() {
        String dadosDpto = "Departamento: " + nome;
        
        if (curso != null) {
            dadosDpto += "\n" + curso.toString();
        } else {
             dadosDpto += "\n  -> Nenhum curso associado (Composição)";
        }
        return dadosDpto;
    }
}