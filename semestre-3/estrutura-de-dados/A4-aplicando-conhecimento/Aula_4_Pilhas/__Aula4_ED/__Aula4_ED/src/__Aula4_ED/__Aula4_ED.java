package __Aula4_ED;

// Classe __Aula4_ED: encarregada de executar alguns testes
// com a estrutura de dados Pilha.
// Autor1: Ivan Carlos Alcântara de Oliveira.
// Modificado por: Jean Marcos Laine

public class __Aula4_ED {

  public static void main(String[] args) {
    try {
      // Criação da Pilha p com tamanho padrão
      Pilha p = new Pilha();
        
      // Inserção de valores na Pilha p
      p.push(4);
      p.push(5);    
      p.push(10);
      p.push(9);
      p.push(8);
            
      // Apresentação dos dados da Pilha p
      System.out.println("Pilha inicial (após algumas inserções):");
      System.out.println(p);
      System.out.println("Distância do 5 em relação ao topo: " + p.search(5));
      System.out.println("Distância do 6 em relação ao topo: " + p.search(6));
      
      // Inversão do conteúdo da pilha      
      p.inverts();  
      System.out.println("\nPilha após a inversão:");
      System.out.println(p);    
      System.out.println("Distância do 5 em relação ao topo: " + p.search(5));
      
      // Remoção dos ímpares da pilha e deixa a pilha na mesma ordem
      // type = 1 remove ímpares
      p.popEvenOdd(1); 
      System.out.println("\nEstado da Pilha depois de removidos os ímpares:");
      System.out.println(p);  
      
      // Inserção de mais valores na Pilha p
      p.push(12);
      p.push(8);
      p.push(22);
      p.push(34);
      System.out.println("\nPilha (após mais inserções):");
      System.out.println(p);
      
      // Remoção dos múltiplos de 4 da pilha e deixa a pilha na mesma ordem
      p.popMultiple(4); 
      System.out.println("\nEstado da Pilha depois de removidos os múltiplos de 4:");
      System.out.println(p);  
      
      // Inserção de mais alguns valores na Pilha p
      p.push(15);
      p.push(6);
      p.push(17);
      p.push(33);
      System.out.println("\nPilha (após mais algumas inserções):");
      System.out.println(p);
      
      // Remoção dos pares da pilha e deixa a pilha na mesma ordem
      // type = 2 remove pares
      p.popEvenOdd(2); 
      System.out.println("\nEstado da Pilha depois de removidos os pares:");
      System.out.println(p);
    
    } catch (Exception e) {
      System.out.println("Erro: " + e.getMessage());
    }
  }

}