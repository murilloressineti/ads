class Aluno {
    private String nome;
    private String nomeCurso;
    private String nivelCurso;
    private int codMatricula;
    private boolean ehBolsista;
    private double mensalidadeBase;
    private double percentualDesconto;

    public Aluno(String nome, String nomeCurso, String nivelCurso, int codMatricula, 
                 boolean ehBolsista, double mensalidadeBase, double percentualDesconto) {
        this.nome = nome;
        this.nomeCurso = nomeCurso;
        this.nivelCurso = nivelCurso;
        this.codMatricula = codMatricula;
        this.ehBolsista = ehBolsista;
        this.mensalidadeBase = mensalidadeBase;
        this.percentualDesconto = percentualDesconto;
    }
    public double calcularMensalidadeFinal() {
        if (ehBolsista) {
            return mensalidadeBase * (1 - percentualDesconto / 100);
        }
        return mensalidadeBase;
    }

    public String getDados() {
        StringBuilder resp = new StringBuilder();
        resp.append("Aluno: ").append(this.nome).append("\n");
        resp.append("Curso: ").append(this.nivelCurso).append(" em ").append(this.nomeCurso).append("\n");
        
        if (ehBolsista) {
            resp.append("Bolsista com desconto de: ").append(this.percentualDesconto).append("%\n");
        }
        
        resp.append("Mensalidade: R$ ").append(String.format("%.2f", calcularMensalidadeFinal()));
        return resp.toString();
    }
}

class Teste {
    public static void main(String args[]) {
        Aluno a1 = new Aluno("Martin Fowler", "ADS", "Graduação", 11223344, true, 920.50, 20.5);
        Aluno a2 = new Aluno("Kent Beck", "Computação Aplicada", "Mestrado", 22334455, false, 2150.45, 0);
        
        System.out.println(a1.getDados());
        System.out.println("\n" + a2.getDados());
    }
}