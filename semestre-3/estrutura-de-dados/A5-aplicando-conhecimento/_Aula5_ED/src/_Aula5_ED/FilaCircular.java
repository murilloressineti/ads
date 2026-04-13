package _Aula5_ED;

public class FilaCircular<T> {
	private static final int TAM_DEFAULT = 100;
	private int inicio, fim;
	private int qtde;
	private T e[];

	public FilaCircular(int tamanho) {
		this.inicio = this.fim = this.qtde = 0;
		e = (T[]) new Object[tamanho];
	}

	public FilaCircular() {
		this(TAM_DEFAULT);
	}

	public boolean isEmpty() {
		return (qtde == 0);
	}

	public boolean isFull() {
		return (qtde == e.length);
	}

	public void enqueue(T e) throws Exception {
		if (!isFull()) {
			this.e[this.fim++] = e;
			this.fim = this.fim % this.e.length;
			this.qtde++;
		} else
			throw new Exception("Oveflow - Estouro de Fila");
	}

	public T dequeue() throws Exception {
		T aux;
		if (!isEmpty()) {
			aux = this.e[this.inicio];
			this.inicio = ++this.inicio % this.e.length;
			this.qtde--;
			return aux;
		} else {
			throw new Exception("underflow - Esvaziamento de Fila");
		}
	}

	public T front() throws Exception {
		if (!isEmpty())
			return e[inicio];
		else {
			throw new Exception("underflow - Esvaziamento de Fila");
		}
	}

	public T rear() throws Exception {
		if (!isEmpty()) {
			int pfinal;
			if (this.fim != 0)
				pfinal = this.fim - 1;
			else
				pfinal = this.e.length - 1;
			return this.e[pfinal];
		} else {
			throw new Exception("underflow - Esvaziamento de Fila");
		}
	}

	public int size() {
		return qtde;
	}

	@Override
	public String toString() {
		try {
			int indiceNovo = (inicio + qtde) % e.length;
			StringBuilder sb = new StringBuilder();
			sb.append("[Fila] quantidade: ")
					.append(qtde)
					.append(", capacidade: ")
					.append(e.length);
			if (qtde != 0) {
				sb.append(", primeiro: ")
						.append(front())
						.append(", último: ")
						.append(rear());
			}
			sb.append("\nConteudo da Fila: [ ");
			if (qtde != 0) {
				if (indiceNovo <= inicio) {
					for (int i = inicio; i < e.length; ++i)
						sb.append("\n[" + e[i] + "]");
					for (int i = 0; i < indiceNovo; ++i)
						sb.append("\n[" + e[i] + "]");
				} else {
					for (int i = inicio; i < indiceNovo; ++i)
						sb.append("\n[" + e[i] + "]");
				}
			}
			sb.append(" ]");
			return sb.toString();
		} catch (Exception e) {
			return ("Erro: " + e.getMessage());
		}
	}
}