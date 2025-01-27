package com.ed1.estoque;

import java.util.Scanner;

/**
 *
 * @author luiz
 */
public class Menu {

    Scanner sc;
    Lista lista = new Lista();
    Conexao c = new Conexao();

    public Menu() {

        this.lista.setLista(c.getLivros());

        sistema:
        while (true) {

            System.out.println("-----------------------------");
            System.out.println("Estoque de Livros");
            System.out.println("-----------------------------");
            System.out.println("[1] Adicionar Livro ao Estoque");
            System.out.println("[2] Remover Livro do Estoque");
            System.out.println("[3] Listar Livros");
            System.out.println("[4] Ordenar Livros");
            System.out.println("[5] Reservar Livro");
            System.out.println("[6] Desfazer Última Operação");
            System.out.println("[7] Salvar Alterações no Banco de Dados");
            System.out.println("\n[0] Encerrar o sistema");
            System.out.println("-----------------------------");
            System.out.print("> ");
            this.sc = new Scanner(System.in);

            try {

                int opcao = sc.nextInt();
                sc.nextLine();
                System.out.println("\n");

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
                    case 7 -> salvarAlteracoesNoBanco();
                    default -> System.out.println("Opção inválida");
                }

                // espera confirmação do usuário para mostrar o menu novamente
                sc.nextLine();

            } catch (java.util.InputMismatchException e) {
                System.out.println("# Insira apenas números por favor.");
            }

            System.out.print("\n");
        }
    }

    private void adicionarLivroAoEstoque() {
        try {
            System.out.println("Adicionar Livro ao Estoque:");
            Livro livro = new Livro();

            System.out.print("Título: ");
            livro.setTitulo(sc.nextLine());

            System.out.print("Autor: ");
            livro.setAutor(sc.nextLine());

            System.out.print("Ano de Publicação: ");
            livro.setAnoDePublicacao(sc.nextInt());
            sc.nextLine();

            System.out.print("Quantidade no Estoque: ");
            livro.setQuantidadeNoEstoque(sc.nextInt());
            sc.nextLine();

            this.lista.inserir(livro);

        } catch (java.util.InputMismatchException e) {
            System.out.println("# Operação cancelada, insira apenas números por favor.");
            sc.nextLine();
        }

    }

    private void removerLivroDoEstoque() {
        System.out.println("Remover Livro do Estoque:");
        System.out.print("Título do livro (ou parte dele): ");
        String titulo = sc.nextLine().toLowerCase();

        Boolean encontrou = false;

        for (Livro l : lista.getLista()) {
            if (l.getTitulo().toLowerCase().contains(titulo)) {

                encontrou = true;

                System.out.println("Livro Encontrado: " + l.getTitulo());
                System.out.println("Autor(a): " + l.getAutor());
                System.out.println("Ano de Publicação: " + l.getAnoDePublicacao());
                System.out.println("Quantidade no Estoque: " + l.getQuantidadeNoEstoque());

                System.out.println("Deseja excluir este livro?");
                System.out.println("[1] Não");
                System.out.println("[9] Sim");
                System.out.print("> ");
                if (!sc.nextLine().equals("9")) {
                    System.out.println("Operação cancelada.");
                } else {
                    this.lista.remover(l.getId());
                    System.out.println("Livro removido com sucesso.");
                }
            }
        }

        if (!encontrou) {
            System.out.println("Não encontrei nenhum livro com esse nome.ã");
        }
    }

    private void listarLivros() {
        
        System.out.println("Listar Livros:");
        
        if (lista.getLista().length == 0) {
            System.out.println("Não há livros a serem exibidos");
            
        } else {
            
            PrettyTable pt = new PrettyTable("Título", "Autor", "Ano de Lançamento", "Quantidade no Estoque");
            for (Livro l : lista.getLista()) {
                pt.addRow(l.getTitulo(), l.getAutor(), String.valueOf(l.getAnoDePublicacao()), String.valueOf(l.getQuantidadeNoEstoque()));
            }

            System.out.println(pt);
        }

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

    private void salvarAlteracoesNoBanco() {
        c.salvarAlteracoes(lista);
    }

}
