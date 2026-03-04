public class Principal {
    public static void main(String[] args) {
        CadastroProdutos cadastro = new CadastroProdutos();

        // Teste 1: Tudo certo
        cadastro.adicionarProduto("Celular", 9500.0, 17); 

        // Teste 2: Nome vazio (Vai dar erro)
        cadastro.adicionarProduto("", 249.99, 15); 

        // Teste 3: Preço negativo (Vai dar erro)
        cadastro.adicionarProduto("Fone Bluetooth", -55.90, 20); 

        // Teste 4: Quantidade negativa (Vai dar erro)
        cadastro.adicionarProduto("Teclado", 150.0, -3); 

        // Mostra apenas o que funcionou
        cadastro.listarProdutos();
    }
}