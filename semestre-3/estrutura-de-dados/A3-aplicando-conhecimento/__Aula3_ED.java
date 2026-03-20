import java.util.Scanner;
import java.util.Arrays;

// Classe __Aula3_ED: encarregada de executar as funcionalidades de ordenação e busca
public class __Aula3_ED {
    private Scanner ler = new Scanner(System.in); 
    public static final int MAX_CANDIDATOS = 15;

    public static void main(String[] args) {
        new __Aula3_ED();  
    }

    public __Aula3_ED() {
        Candidato[] candidatos = new Candidato[MAX_CANDIDATOS];
        
        // 1. Inicializa o cadastro
        criarCadastroCandidatos(candidatos);
        
        System.out.println("--- LISTA ORIGINAL ---");
        mostrarTodosOsCandidatos(candidatos);

        // 2. Criar cópias independentes para cada algoritmo de ordenação
        Candidato[] candidatosBubble = Arrays.copyOf(candidatos, candidatos.length);
        Candidato[] candidatosInsertion = Arrays.copyOf(candidatos, candidatos.length);
        Candidato[] candidatosSelection = Arrays.copyOf(candidatos, candidatos.length);

        // 3. Teste de Pesquisa Sequencial (no vetor original/desordenado)
        System.out.println("\n--- TESTE: PESQUISA SEQUENCIAL ---");
        System.out.print("Digite o nome do candidato para busca sequencial: ");
        String nomeBuscaSeq = ler.nextLine();
        Candidato resultadoSeq = pesquisaSequencial(nomeBuscaSeq, candidatos);
        if (resultadoSeq != null) {
            System.out.println("Encontrado: " + resultadoSeq);
        } else {
            System.out.println("Candidato não encontrado.");
        }

        // 4. Bubble Sort (Ordenação por NOME)
        System.out.println("\n--- EXECUTANDO BUBBLE SORT (POR NOME) ---");
        bubbleSort(candidatosBubble);
        mostrarTodosOsCandidatos(candidatosBubble);

        // 5. Teste de Pesquisa Binária (DEVE ser feito no vetor ordenado por nome)
        System.out.println("\n--- TESTE: PESQUISA BINÁRIA ---");
        System.out.print("Digite o nome do candidato para busca binária: ");
        String nomeBuscaBin = ler.nextLine();
        Candidato resultadoBin = pesquisaBinaria(nomeBuscaBin, candidatosBubble);
        if (resultadoBin != null) {
            System.out.println("Encontrado: " + resultadoBin);
        } else {
            System.out.println("Candidato não encontrado.");
        }

        // 6. Insertion Sort (Ordenação por IDADE)
        System.out.println("\n--- EXECUTANDO INSERTION SORT (POR IDADE) ---");
        insertionSort(candidatosInsertion);
        mostrarTodosOsCandidatos(candidatosInsertion);

        // 7. Selection Sort (Ordenação por NÚMERO)
        System.out.println("\n--- EXECUTANDO SELECTION SORT (POR NÚMERO) ---");
        selectionSort(candidatosSelection);
        mostrarTodosOsCandidatos(candidatosSelection);
    }

    public void criarCadastroCandidatos(Candidato[] candidatos) {    
        candidatos[0] = new Candidato(1111, "111.111.111-11", "Leonardo", 18, 'M', "rock");
        candidatos[1] = new Candidato(8888, "888.888.888-88", "Luiza", 21, 'F', "romantica");
        candidatos[2] = new Candidato(7777, "777.777.777-77", "Pedro", 22, 'M', "rock");
        candidatos[3] = new Candidato(6666, "666.666.666-66", "Ana Lopes", 54, 'F', "sertanejo");
        candidatos[4] = new Candidato(3333, "333.333.333-33", "Mariana", 25, 'F', "sertanejo");
        candidatos[5] = new Candidato(4444, "444.444.444-44", "Amilton", 35, 'M', "rock");
        candidatos[6] = new Candidato(2222, "222.222.222-22", "Juliano", 19, 'M', "forró");    
        candidatos[7] = new Candidato(5555, "555.555.555-55", "Luciano", 52, 'M', "forró");
        candidatos[8] = new Candidato(1010, "101.101.101-10", "Christiane", 35, 'F', "rock");    
        candidatos[9] = new Candidato(9999, "999.999.999-99", "Luis Alberto C.", 18, 'M', "rap");
        
        // Adicionando os 5 candidatos restantes para completar 15
        candidatos[10] = new Candidato(1212, "121.121.121-12", "Zeca", 30, 'M', "samba");
        candidatos[11] = new Candidato(1313, "131.131.131-13", "Beatriz", 22, 'F', "pop");
        candidatos[12] = new Candidato(1414, "141.141.141-14", "Carlos", 28, 'M', "jazz");
        candidatos[13] = new Candidato(1515, "151.151.151-15", "Daniela", 19, 'F', "axé");
        candidatos[14] = new Candidato(1616, "161.161.161-16", "Eduardo", 40, 'M', "blues");
    }

    public void mostrarTodosOsCandidatos(Candidato[] cand) {
        if (cand == null || cand.length == 0) {
            System.out.println("Não há candidatos cadastrados!");
            return;
        }
        System.out.println("\nCandidatos cadastrados:");
        for (Candidato c : cand) {
            if (c != null) System.out.println(c.toString());
        }
    }

    public Candidato pesquisaSequencial(String nomeCandidato, Candidato[] cand) {
        int comparacoes = 0;
        for (int i = 0; i < cand.length; i++) {
            comparacoes++;
            if (cand[i].getNome().equalsIgnoreCase(nomeCandidato)) {
                System.out.println(">>> Comparações realizadas (Sequencial): " + comparacoes);
                return cand[i];
            }
        }
        System.out.println(">>> Comparações realizadas (Sequencial): " + comparacoes);
        return null;
    }

    public Candidato pesquisaBinaria(String nomeCandidato, Candidato[] cand) {
        int comparacoes = 0;
        int inicio = 0;
        int fim = cand.length - 1;

        while (inicio <= fim) {
            comparacoes++;
            int meio = (inicio + fim) / 2;
            int resultadoComp = cand[meio].getNome().compareToIgnoreCase(nomeCandidato);

            if (resultadoComp == 0) {
                System.out.println(">>> Comparações realizadas (Binária): " + comparacoes);
                return cand[meio];
            } else if (resultadoComp < 0) {
                inicio = meio + 1;
            } else {
                fim = meio - 1;
            }
        }
        System.out.println(">>> Comparações realizadas (Binária): " + comparacoes);
        return null;
    }

    public void selectionSort(Candidato[] cand) {
        int trocas = 0;
        for (int i = 0; i < cand.length - 1; i++) {
            int menorPos = i;
            for (int j = i + 1; j < cand.length; j++) {
                if (cand[j].getNumero_candidato() < cand[menorPos].getNumero_candidato()) {
                    menorPos = j;
                }
            }
            if (menorPos != i) {
                Candidato temp = cand[i];
                cand[i] = cand[menorPos];
                cand[menorPos] = temp;
                trocas++;
            }
        }
        System.out.println(">>> Total de trocas (Selection Sort por Número): " + trocas);
    }

    public void insertionSort(Candidato[] cand) {
        int trocas = 0;
        for (int i = 1; i < cand.length; i++) {
            Candidato pivo = cand[i];
            int j = i - 1;
            while (j >= 0 && cand[j].getIdade() > pivo.getIdade()) {
                cand[j + 1] = cand[j];
                j--;
                trocas++; // Cada deslocamento é contado como parte do processo de troca/reordenação
            }
            cand[j + 1] = pivo;
        }
        System.out.println(">>> Total de trocas/deslocamentos (Insertion Sort por Idade): " + trocas);
    }

    public void bubbleSort(Candidato[] cand) {
        int trocas = 0;
        for (int i = 0; i < cand.length - 1; i++) {
            for (int j = 0; j < cand.length - 1 - i; j++) {
                if (cand[j].getNome().compareToIgnoreCase(cand[j + 1].getNome()) > 0) {
                    Candidato temp = cand[j];
                    cand[j] = cand[j + 1];
                    cand[j + 1] = temp;
                    trocas++;
                }
            }
        }
        System.out.println(">>> Total de trocas (Bubble Sort por Nome): " + trocas);
    }
}