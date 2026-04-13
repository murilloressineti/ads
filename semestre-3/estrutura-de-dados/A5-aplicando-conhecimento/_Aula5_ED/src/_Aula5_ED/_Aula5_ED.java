package _Aula5_ED;

/**
 * Classe _Aula5_ED: Responsável por testar e demonstrar funcionalidades
 * da estrutura de dados Fila Circular com objetos do tipo Imóvel.
 * * Autores: Ivan Carlos Alcântara de Oliveira e Jean Marcos Laine
 * Aluno: <COLOQUE O SEU NOME E RA AQUI>
 * 
 * @version 1.1
 */

public class _Aula5_ED {

	public static void bancoDeDados(FilaCircular<Imovel> f) throws Exception {
		f.enqueue(new Imovel(1, 1998, 'C', 2000000.0f, 450.0f));
		f.enqueue(new Imovel(2, 2005, 'S', 10000000.0f, 700.0f));
		f.enqueue(new Imovel(3, 2008, 'A', 500000.0f, 80.0f));
		f.enqueue(new Imovel(4, 2012, 'F', 33000000.0f, 2000.0f));
		f.enqueue(new Imovel(5, 2000, 'S', 20000000.0f, 800.0f));
		f.enqueue(new Imovel(6, 2024, 'A', 400000.0f, 45.0f));
		f.enqueue(new Imovel(7, 2021, 'F', 8000000.0f, 1000.0f));
		f.enqueue(new Imovel(8, 2001, 'A', 450000.0f, 50.0f));
		f.enqueue(new Imovel(9, 1999, 'C', 500000.0f, 80.0f));
		f.enqueue(new Imovel(10, 2016, 'C', 1000000.0f, 100.0f));
	}

	public static void removeImovel(FilaCircular<Imovel> f, char tipo) throws Exception {
		int total = f.size();
		for (int i = 0; i < total; i++) {
			Imovel temp = f.dequeue();
			if (temp.getTipo() != tipo) {
				f.enqueue(temp);
			}
		}
	}

	public static void exibeImovelAnoArea(FilaCircular<Imovel> f, int anoDeConstrucao, float area) throws Exception {
		int total = f.size();
		for (int i = 0; i < total; i++) {
			Imovel temp = f.dequeue();
			if (temp.getAnoDeConstrucao() >= anoDeConstrucao && temp.getArea() > area) {
				System.out.println(temp);
			}
			f.enqueue(temp);
		}
	}

	public static void exibeImovelAnoPreco(FilaCircular<Imovel> f, int anoDeConstrucao, float preco) throws Exception {
		int total = f.size();
		for (int i = 0; i < total; i++) {
			Imovel temp = f.dequeue();
			if (temp.getAnoDeConstrucao() < anoDeConstrucao && temp.getPreco() < preco) {
				System.out.println(temp);
			}
			f.enqueue(temp);
		}
	}

	public static void ordenarImoveisPorPreco(FilaCircular<Imovel> f) throws Exception {
		int n = f.size();
		if (n <= 1)
			return;

		// Bubble Sort adaptado para Fila Circular
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n - 1; j++) {
				Imovel atual = f.dequeue();
				Imovel proximo = f.dequeue();

				if (atual.getPreco() > proximo.getPreco()) {
					f.enqueue(proximo); // O menor volta primeiro
					f.enqueue(atual);
				} else {
					f.enqueue(atual);
					f.enqueue(proximo);
				}
				// Rotaciona para comparar o próximo par
				for (int k = 0; k < n - 2; k++) {
					f.enqueue(f.dequeue());
				}
			}
		}

		// Impressão da fila ordenada conforme solicitado
		System.out.println("[Fila ordenada por preço]:");
		int total = f.size();
		for (int i = 0; i < total; i++) {
			Imovel temp = f.dequeue();
			System.out.println(temp);
			f.enqueue(temp);
		}
	}

	public static void main(String[] args) {
		try {
			FilaCircular<Imovel> f = new FilaCircular<Imovel>();
			bancoDeDados(f);
			System.out.println(f);
			System.out.println("\nAno >= 2000 e Area > 300m2");
			exibeImovelAnoArea(f, 2000, 300.0f);
			System.out.println("\nAno < 2023 e Preço < R$ 10.000.000,00");
			exibeImovelAnoPreco(f, 2023, 10000000.0f);
			System.out.println("\nFila após a remoção dos imóveis tipo 'C' (casa):");
			removeImovel(f, 'C');
			System.out.println(f);
			System.out.println("\nApós ordenar imóveis por preço:");
			ordenarImoveisPorPreco(f);
		} catch (Exception e) {
			System.out.println("Erro: " + e.getMessage());
		}
	}
}