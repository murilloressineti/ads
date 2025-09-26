public class SomaVetor implements Runnable {
    private int[] vetor;
    private int inicio;
    private int fim;
    private int resultadoParcial;

    public SomaVetor(int[] vetor, int inicio, int fim) {
        this.vetor = vetor;
        this.inicio = inicio;
        this.fim = fim;
        this.resultadoParcial = 0;
    }

    @Override
    public void run() {
        for (int i = inicio; i < fim; i++) {
            resultadoParcial += vetor[i];
        }
    }

    public int getResultadoParcial() {
        return resultadoParcial;
    }

    public static void main(String[] args) throws InterruptedException {
        int tamanhoVetor = 100;
        int[] vetor = new int[tamanhoVetor];
        
        // Preenchendo o vetor com valores de 1 a 100
        for (int i = 0; i < tamanhoVetor; i++) {
            vetor[i] = i + 1;
        }

        // Criando as duas threads
        SomaVetor somaMetade1 = new SomaVetor(vetor, 0, tamanhoVetor / 2);
        SomaVetor somaMetade2 = new SomaVetor(vetor, tamanhoVetor / 2, tamanhoVetor);

        Thread thread1 = new Thread(somaMetade1);
        Thread thread2 = new Thread(somaMetade2);

        // Iniciando as threads
        thread1.start();
        thread2.start();

        // Aguardando a finalização das threads
        thread1.join();
        thread2.join();

        // Somando os resultados parciais
        int resultadoFinal = somaMetade1.getResultadoParcial() + somaMetade2.getResultadoParcial();

        System.out.println("Soma da primeira metade: " + somaMetade1.getResultadoParcial());
        System.out.println("Soma da segunda metade: " + somaMetade2.getResultadoParcial());
        System.out.println("Soma total do vetor: " + resultadoFinal);
    }
}