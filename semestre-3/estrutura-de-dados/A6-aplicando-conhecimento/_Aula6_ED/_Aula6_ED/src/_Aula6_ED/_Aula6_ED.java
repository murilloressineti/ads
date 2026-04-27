package _Aula6_ED;

import java.util.Scanner;

public class _Aula6_ED {
    private static Scanner ler = new Scanner(System.in);

    public static void bancoDeDados(LinkedList<Trabalhador> func) {
        func.addLast(new Trabalhador(1, 25, 'M', 5000.0f, "Analista Jr."));
        func.addLast(new Trabalhador(2, 22, 'M', 3000.0f, "Auxiliar Administrativo."));
        func.addLast(new Trabalhador(3, 27, 'F', 5000.0f, "Analista Jr."));
        func.addLast(new Trabalhador(4, 35, 'M', 7000.0f, "Programador Sr."));
        func.addLast(new Trabalhador(5, 45, 'F', 7000.0f, "Gerente Jr."));
        func.addLast(new Trabalhador(6, 51, 'M', 7500.0f, "Gerente Pleno"));
        func.addLast(new Trabalhador(7, 60, 'M', 8000.0f, "Vice-Presidente"));
        func.addLast(new Trabalhador(8, 34, 'F', 7000.0f, "Analista Pleno"));
        func.addLast(new Trabalhador(9, 43, 'F', 8000.0f, "Programador Sr."));
        func.addLast(new Trabalhador(10, 63, 'F', 10000.0f, "Presidente"));
    }

    public static void demiteFuncionarios(LinkedList<Trabalhador> func) {
        String resposta;
        do {
            System.out.println("Deseja demitir um funcionário (S/N)?");
            resposta = ler.next();
            if (resposta.equalsIgnoreCase("S")) {
                System.out.println("Forneça o número do funcionário: ");
                int id = ler.nextInt();
                if (func.removePorId(id)) {
                    System.out.println("Funcionário " + id + " encontrado e removido com sucesso!");
                } else {
                    System.out.println("Funcionário " + id + " não foi encontrado!");
                }
            }
        } while (resposta.equalsIgnoreCase("S"));
    }

    public static void main(String[] args) {
        LinkedList<Trabalhador> func = new LinkedList<>();
        bancoDeDados(func);
        System.out.println("Lista inicial de funcionários:" + func);

        LinkedList<Trabalhador> contratados = new LinkedList<>();
        contratados.addLast(new Trabalhador(11, 23, 'M', 3900.0f, "Programador Jr."));
        contratados.addLast(new Trabalhador(12, 29, 'F', 4500.0f, "Secretária Bilíngue"));
        contratados.addLast(new Trabalhador(13, 35, 'M', 6000.0f, "Programador Pleno"));
        contratados.addLast(new Trabalhador(14, 53, 'M', 5000.0f, "Programador Jr."));
        contratados.addLast(new Trabalhador(15, 63, 'F', 8000.0f, "Analista Sr."));
        contratados.addLast(new Trabalhador(16, 56, 'M', 9000.0f, "Analista Sr."));

        func.concatena(contratados);
        System.out.println("\nLista após contratações:" + func);

        System.out.println("\n=== TESTE 1: Atualização de salário ===");
        boolean sucesso = func.atualizaSalario(11, 4200.0f);
        System.out.println("Atualização realizada? " + (sucesso ? "Sim" : "Não"));

        System.out.println("\n=== TESTE 2: Filtragem por cargo ===");
        System.out.println("Filtrando 'Programador Jr.':" + func.filtrarPorCargo("Programador Jr."));

        System.out.println("\n=== PROCESSO DE DEMISSÕES ===");
        demiteFuncionarios(func);
        System.out.println("\nLista final após demissões:" + func);
    }
}