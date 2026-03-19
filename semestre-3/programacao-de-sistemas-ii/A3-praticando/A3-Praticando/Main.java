import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Livro> listaLivros = new ArrayList<>();
        listaLivros.add(new Livro("Java: Como Programar", "Deitel", 2017, 250.00));
        listaLivros.add(new Livro("Use a Cabeça! Java", "Kathy Sierra", 2024, 180.00));
        listaLivros.add(new Livro("Engenharia de Software", "Pressman", 2021, 300.00));

        String nomeArquivo = "livros.ser";

        // Serialização
        try (FileOutputStream fos = new FileOutputStream(nomeArquivo);
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(listaLivros);
            System.out.println("Arquivo '" + nomeArquivo + "' criado com sucesso!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Desserialização
        try (FileInputStream fis = new FileInputStream(nomeArquivo);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            
            @SuppressWarnings("unchecked")
            List<Livro> listaRecuperada = (List<Livro>) ois.readObject();
            
            System.out.println("\n--- Livros Recuperados do Arquivo ---");
            for (Livro livro : listaRecuperada) {
                System.out.println(livro);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}