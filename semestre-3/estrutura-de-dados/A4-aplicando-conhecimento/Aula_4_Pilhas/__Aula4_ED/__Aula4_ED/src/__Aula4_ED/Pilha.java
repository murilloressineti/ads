package __Aula4_ED;

// Classe relativa a estrutura de dados Pilha 
// sequencial capaz de armazenar dados inteiros
// Autor1: Ivan Carlos Alcântara de Oliveira.
// Modificado por: Murillo Ressineti Silva
// Autor2: 10732430

public class Pilha  {
  private static final int TAM_DEFAULT = 100; // Capacidade padrão da pilha
  private int topo; // Índice do elemento no topo (-1 = pilha vazia)
  private int e[]; // array de inteiros correspondente a Pilha
    
  /**
     * Construtor com tamanho personalizado
     * @param tamanho Capacidade inicial da pilha (deve ser > 0)
     */
    public Pilha(int tamanho) {  
      this.e = new int[tamanho];
      this.topo = -1;
    }
  
    /**
    * Construtor padrão que usa capacidade definida por TAM_DEFAULT
    */
    public Pilha() {
      this(TAM_DEFAULT);
    } 
    
    /**
     * Verifica se a pilha está vazia
     * @return true se não há elementos, false caso contrário
     */
    public boolean isEmpty() {
      return this.topo == -1; 
    }
    
  /**
     * Verifica se a pilha está cheia
     * @return true se a capacidade máxima foi atingida
     */
    public boolean isFull() {
      return this.topo == this.e.length-1;
    }
    
    /**
     * Esvazia completamente a pilha
     * @throws Exception Se ocorrer erro durante a operação
     */
    public void clear() throws Exception{
      while (!isEmpty()) {
        pop();
      }
    }
   
  /**
     * Insere um elemento no topo da pilha
     * @param e Elemento a ser inserido
     * @throws Exception Se a pilha estiver cheia (Stack Overflow)
     */
    public void push(int e) throws Exception{
      if (! this.isFull( ))
        this.e[++this.topo] = e;
      else 
        throw new Exception("Stack Overflow");
    }
    
  /**
     * Remove e retorna o elemento do topo
     * @return Elemento removido
     */
    public int pop() throws Exception{
      if (! this.isEmpty( ))
        return this.e[this.topo--];
      else{
        throw new Exception("Stack Underflow");
      }  
    }
    
  /**
     * Retorna o elemento do topo sem remover
     * @return Elemento no topo
     * @throws Exception Se a pilha estiver vazia
     */
    public int peek() throws Exception{
    if ( ! this.isEmpty( ))
      return this.e[this.topo];
    else{
      throw new Exception("Stack Underflow");
    }
    }
  
    /**
     * Retorna a quantidade de elementos armazenados
     * @return Número de elementos na pilha
     */
    public int size() {
    return topo+1;
    }
        
    /**
     * Busca a primeira ocorrência de um elemento a partir do topo
     * @param e Elemento a ser buscado
     * @return Distância do topo (0-based) ou -1 se não encontrado
     */
  public int search(int e) {
    // Percorre do topo para a base para encontrar a primeira ocorrência
    for (int i = this.topo; i >= 0; i--) {
        if (this.e[i] == e) {
            return this.topo - i; // Retorna a distância em relação ao topo
        }
    }
    return -1;
  }

  /**
     * Inverte completamente a ordem dos elementos da pilha
     * @throws Exception Se ocorrer erro durante a operação
     */
  public void inverts() throws Exception {
    if (isEmpty()) return;
    
    int i = 0;
    int j = this.topo;
    // Troca os elementos das extremidades em direção ao centro
    while (i < j) {
        int temp = this.e[i];
        this.e[i] = this.e[j];
        this.e[j] = temp;
        i++;
        j--;
    }
  }
  
  /**
     * Remove elementos pares ou ímpares mantendo a ordem original
     * @param type 1 para remover ímpares, 2 para pares
     * @throws Exception Se type for inválido ou pilha vazia
     */
  public void popEvenOdd(int type) throws Exception {
    if (type != 1 && type != 2) {
        throw new Exception("The parameter to the popEvenOdd method must be 1 for odd and 2 for even");
    }

    Pilha aux = new Pilha(this.e.length);
    
    // Desempilha e guarda na auxiliar apenas o que NÃO deve ser removido
    while (!isEmpty()) {
        int valor = pop();
        boolean remover = false;
        
        if (type == 1 && valor % 2 != 0) remover = true; // Impar
        if (type == 2 && valor % 2 == 0) remover = true; // Par
        
        if (!remover) {
            aux.push(valor);
        }
    }
    
    // Devolve para a pilha original (isso restaura a ordem correta)
    while (!aux.isEmpty()) {
        this.push(aux.pop());
    }
  }
  
  /**
     * Remove elementos múltiplos do número especificado
     * @param nro Divisor para identificação de múltiplos
     * @throws Exception Se nro for inválido (ex: zero)
     */
  public void popMultiple(int nro) throws Exception {
    if (nro == 0) throw new Exception("Divisor cannot be zero");

    Pilha aux = new Pilha(this.e.length);
    
    // Filtra os múltiplos usando a pilha auxiliar
    while (!isEmpty()) {
        int valor = pop();
        if (valor % nro != 0) {
            aux.push(valor);
        }
    }
    
    // Restaura os elementos que sobraram
    while (!aux.isEmpty()) {
        this.push(aux.pop());
    }
  }
    
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("  [Stack] top: ")
      .append(this.topo)
      .append(", capacity: ")
      .append(e.length)
      .append(", size: ")
      .append(size());
    if (this.topo != -1) {
      sb.append(", Top value: ")
        .append(this.e[this.topo]);
    }else sb.append(", \r\n" + "Top value: EMPTY STACK");

    sb.append("\n  Stack Contents: [ ");
      
    for (int i = this.topo; i >= 0; --i)
      if (i != 0) sb.append( e[i] + ", ");
      else sb.append( e[i] );
    sb.append( " ]");
    return sb.toString();
  }

}