package _Aula6_ED;

public class LinkedList<T> {
    private Node<T> head;
    private int size;

    public LinkedList() {
        head = null;
        size = 0;
    }

    public boolean isEmpty() { return head == null; }
    public boolean isFull() { return false; }
    public int getSize() { return size; }
    public Node<T> getHead() { return head; }

    public boolean addLast(T id) {
        Node<T> aux = new Node<>(id, null);
        if (isEmpty()) {
            head = aux;
        } else {
            Node<T> pAnda = head;
            while (pAnda.getProx() != null) pAnda = pAnda.getProx();
            pAnda.setProx(aux);
        }
        size++;
        return true;
    }

    // --- MÉTODOS SOLICITADOS NA ATIVIDADE ---

    public void concatena(LinkedList<T> lista) {
        if (lista.isEmpty()) return;
        if (this.isEmpty()) {
            this.head = lista.getHead();
        } else {
            Node<T> pAnda = this.head;
            while (pAnda.getProx() != null) pAnda = pAnda.getProx();
            pAnda.setProx(lista.getHead());
        }
        this.size += lista.getSize();
    }

    public boolean atualizaSalario(int id, float novoSalario) {
        Node<T> pAnda = head;
        while (pAnda != null) {
            if (pAnda.getDado() instanceof Trabalhador) {
                Trabalhador t = (Trabalhador) pAnda.getDado();
                if (t.getId() == id) {
                    t.setSalario(novoSalario);
                    return true;
                }
            }
            pAnda = pAnda.getProx();
        }
        return false;
    }

    public LinkedList<T> filtrarPorCargo(String cargo) {
        LinkedList<T> novaLista = new LinkedList<>();
        Node<T> pAnda = head;
        while (pAnda != null) {
            if (pAnda.getDado() instanceof Trabalhador) {
                Trabalhador t = (Trabalhador) pAnda.getDado();
                if (t.getCargo().equalsIgnoreCase(cargo)) {
                    novaLista.addLast((T) t);
                }
            }
            pAnda = pAnda.getProx();
        }
        return novaLista;
    }

    public boolean removePorId(int id) {
        Node<T> pAnda = head;
        Node<T> pAnt = null;
        while (pAnda != null) {
            if (pAnda.getDado() instanceof Trabalhador) {
                Trabalhador t = (Trabalhador) pAnda.getDado();
                if (t.getId() == id) {
                    if (pAnt == null) head = pAnda.getProx();
                    else pAnt.setProx(pAnda.getProx());
                    size--;
                    return true;
                }
            }
            pAnt = pAnda;
            pAnda = pAnda.getProx();
        }
        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n[Lista]\nL: [ ");
        Node<T> pAnda = head;
        while (pAnda != null) {
            sb.append(pAnda.getDado()).append(" ");
            pAnda = pAnda.getProx();
        }
        sb.append("]\nQtde.: ").append(size).append("\n");
        return sb.toString();
    }
}