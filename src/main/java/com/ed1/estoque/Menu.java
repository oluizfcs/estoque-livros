package com.ed1.estoque;

import java.util.Scanner;

/**
 *
 * @author luiz
 */
public class Menu {

    public Menu() {
        sistema:
        while (true) {
            System.out.println("Estoque de Livros");
            System.out.println("-----------------------------");
            System.out.println("[1] Adicionar Livro ao Estoque");
            System.out.println("[2] Remover Livro do Estoque");
            System.out.println("[3] Listar Livros");
            System.out.println("[4] Ordenar Livros");
            System.out.println("[5] Reservar Livro");
            System.out.println("[6] Desfazer Última Operação");
            System.out.println("\n[0] Encerrar o sistema");
            System.out.println("-----------------------------");
            System.out.print("Escolha uma opção: ");
            Scanner sc = new Scanner(System.in);
            try {
                int opcao = sc.nextInt();
                switch (opcao) {
                    case 0 -> {
                        break sistema;
                    }
                    case 1 -> adicionarLivroAoEstoque();
                    case 2 -> removerLivroDoEstoque();
                    case 3 -> listarLivros();
                    case 4 -> ordenarLivros();
                    case 5 -> reservarLivro();
                    case 6 -> desfazerUltimaAlteracao();
                    default -> System.out.println("Opção inválida");
                }
            } catch (java.util.InputMismatchException e) {
                System.out.println("# Insira apenas números por favor. #");
            }
            System.out.print("\n\n\n");
        }
    }
    
    private void adicionarLivroAoEstoque() {
        System.out.println("Adicionando livro ao estoque...");
        // Lógica para adicionar livro ao estoque
    }
    
    private void removerLivroDoEstoque() {
        System.out.println("Removendo livro do estoque...");
        // Lógica para remover livro do estoque
    }
    
    private void listarLivros() {
        System.out.println("Listando livros...");
        // Lógica para listar os livros
    }
    
    private void ordenarLivros() {
        System.out.println("Ordenando livros...");
        // Lógica para ordenar os livros
    }
    
    private void reservarLivro() {
        System.out.println("Reservando livro...");
        // Lógica para reservar um livro
    }
    
    private void desfazerUltimaAlteracao() {
        System.out.println("Desfazendo última alteração...");
        // Lógica para desfazer a última operação
    }

}
