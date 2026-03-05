class Aluno {
    private String nome;
    private String nomeCurso;
    private String nivelCurso;
    private int codMatricula;
    private int anoFinalizacao;
    private boolean ehBolsista;
    private boolean cursoFinalizado;
    private boolean temTrabalho;
    private double mensalidadeBase;
    private double percentualDesconto;
    private String[] dadosTrabalho; 

    public Aluno(String nome, String nomeCurso, String nivelCurso, int codMatricula,
                 boolean ehBolsista, boolean cursoFinalizado, boolean temTrabalho,
                 int anoFinalizacao, double mensalidadeBase, double percentualDesconto,
                 String[] dadosTrabalho) {
        this.nome = nome;
        this.nomeCurso = nomeCurso;
        this.nivelCurso = nivelCurso;
        this.codMatricula = codMatricula;
        this.ehBolsista = ehBolsista;
        this.cursoFinalizado = cursoFinalizado;
        this.temTrabalho = temTrabalho;
        this.anoFinalizacao = anoFinalizacao;
        this.mensalidadeBase = mensalidadeBase;
        this.percentualDesconto = percentualDesconto;
        this.dadosTrabalho = dadosTrabalho;
    }

    // Extração da lógica de cálculo de mensalidade
    private double calcularMensalidadeFinal() {
        if (ehBolsista) {
            return mensalidadeBase * (1 - percentualDesconto / 100);
        }
        return mensalidadeBase;
    }

    // Extração da lógica complexa de nomes de trabalhos acadêmicos
    private String getTipoTrabalho() {
        switch (nivelCurso) {
            case "Graduação": return "TCC";
            case "Lato sensu": return "Monografia";
            case "Mestrado": return "Dissertação";
            case "Doutorado": return "Tese";
            default: return "Trabalho Final";
        }
    }

    // Método auxiliar para formatar os dados do trabalho
    private String formatarDadosTrabalho() {
        if (!cursoFinalizado || !temTrabalho) return "";

        // Melhorando a legibilidade das posições do array (Rename implícito)
        String orientador = dadosTrabalho[0];
        String titulo = dadosTrabalho[1];
        String nota = dadosTrabalho[2];

        return "Título do " + getTipoTrabalho() + ": " + titulo + "\n" +
               "Orientador(a): Prof. " + orientador + "\n" +
               "Nota: " + nota + "\n";
    }

    public String getDados() {
        StringBuilder sb = new StringBuilder();
        sb.append("Aluno: ").append(nome).append("\n");
        sb.append("Curso: ").append(nivelCurso).append(" em ").append(nomeCurso).append("\n");
        
        if (ehBolsista) {
            sb.append("Bolsista com desconto de: ").append(percentualDesconto).append("%\n");
        }
        sb.append("Mensalidade: R$ ").append(String.format("%.2f", calcularMensalidadeFinal())).append("\n");

        if (cursoFinalizado) {
            sb.append("Curso finalizado em ").append(anoFinalizacao).append("\n");
            sb.append(formatarDadosTrabalho());
        }

        return sb.toString();
    }
}

class Teste {
    public static void main(String args[]) {
        String[] semTrabalho = {null, null, null};
        Aluno aluno1 = new Aluno("Martin Fowler", "ADS", "Graduação", 11223344, 
                                 true, false, false, -1, 920.50, 20.5, semTrabalho);
                            
        String[] comTrabalho = {"Peter Coad", "Impacto da Refatoração", "9.5"};
        Aluno aluno2 = new Aluno("Kent Beck", "Computação Aplicada", "Mestrado", 22334455, 
                                 false, true, true, 2019, 2150.45, 0, comTrabalho);

        System.out.println(aluno1.getDados());
        System.out.println("---------------------------");
        System.out.println(aluno2.getDados());
    }
}