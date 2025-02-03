package com.ed1.estoque;

import com.ed1.estoque.ed.Fila;
import com.ed1.estoque.ed.Lista;
import com.ed1.estoque.ed.Pilha;
import com.ed1.estoque.ordenacao.BubbleSort;
import com.ed1.estoque.ordenacao.SelectionSort;
import java.util.Scanner;

/**
 *
 * @author luiz
 */
public class Menu {

    Scanner sc;
    Lista lista = new Lista();
    Fila fila = new Fila();
    Pilha pilha = new Pilha();
    Conexao conn = new Conexao();

    public Menu() {

        this.lista.setLista(conn.getLivros());
        this.fila.setFila(conn.getFila());
        this.pilha = new Pilha();

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
                    case 3 -> listarLivros(lista.getLivros());
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
            pilha.inserir(new Object[] {livro, 1});

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

        for (Livro l : lista.getLivros()) {
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
                    pilha.inserir(new Object[] {l, 2});
                    this.lista.remover(l.getId());
                    System.out.println("Livro removido com sucesso.");
                }
            }
        }

        if (!encontrou) {
            System.out.println("Não encontrei nenhum livro com esse nome");
        }
    }

    private void listarLivros(Livro[] livros) {

        System.out.println("Listar Livros:");

        if (livros.length == 0) {
            System.out.println("Não há livros a serem exibidos");

        } else {

            PrettyTable pt = new PrettyTable("Título", "Autor", "Ano de Publicação", "Quantidade no Estoque");
            for (Livro l : livros) {
                pt.addRow(l.getTitulo(), l.getAutor(), String.valueOf(l.getAnoDePublicacao()), String.valueOf(l.getQuantidadeNoEstoque()));
            }

            System.out.println(pt);
        }

    }

    private void ordenarLivros() {
        
        System.out.println("Ordenar Livros por:");
        System.out.println("[1] Título");
        System.out.println("[2] Ano de publicação");
        System.out.print("> ");
        String opcao = sc.nextLine();

        switch (opcao) {
            case "1":
                BubbleSort bs = new BubbleSort(lista.getLivros());
                listarLivros(bs.ordenar());
                break;
            case "2":
                SelectionSort ss = new SelectionSort(lista.getLivros());
                listarLivros(ss.ordenar());
                break;
            default:
                System.out.println("Opção inválida\n");
                ordenarLivros();
        }
    }

    private void reservarLivro() {
        
        System.out.println("[1] Reservar Livro");
        System.out.println("[2] Processar reserva");
        System.out.println("[3] Ver fila de espera");
        System.out.print("> ");
        String opcao = sc.nextLine();

        switch (opcao) {
            case "1":
                Lista semEstoque = new Lista();
                
                for(Livro l : lista.getLivros()) {
                    if(l.getQuantidadeNoEstoque() == 0) {
                        semEstoque.inserir(l);
                    }
                }

                if(semEstoque.getLivros().length != 0) {
                    listarLivros(semEstoque.getLivros());

                    System.out.print("Título do livro a ser reservado: ");
                    String titulo = sc.nextLine().toLowerCase();

                    Boolean encontrou = false;
                    for(Livro l : semEstoque.getLivros()) {

                        if(l.getTitulo().toLowerCase().contains(titulo)) {
                            encontrou = true;
                            System.out.println("Insira o nome da pessoa que está reservando: \n" + l.getTitulo());
                            System.out.print("> ");
                            String pessoa = sc.nextLine();
                            fila.inserir(pessoa);
                            System.out.println("\nLivro reservado com sucesso!");
                        }
                    }

                    if(!encontrou) {
                        System.out.println("O título inserido não corresponde a nenhum dos livros fora de estoque.\n");
                        reservarLivro();
                    }


                } else {
                    System.out.println("\nNão há livros fora de estoque para serem reservados.");
                }
                
                break;
            case "2":
                
                String saiu = fila.remover();
                
                if(saiu != null) {
                    System.out.println(saiu + " teve sua reserva processada");
                }

                break;
            case "3":
                fila.listar();
                break;
            default:
                System.out.println("Opção inválida\n");
                reservarLivro();
        }
    }

    private void desfazerUltimaAlteracao() {
        Object[] saiu = pilha.remover();
        
        if(saiu != null) {
            
            Livro livro = (Livro) saiu[0];
            int acao = (int) saiu[1];
            
            if(acao == 1) {
                System.out.println("Inserção desfeita - Livro: \"" + livro.getTitulo() + "\"");
                this.lista.remover(livro.getId());
            } else if (acao == 2) {
                System.out.println("Remoção desfeita - Livro: \"" + livro.getTitulo() + "\"");
                this.lista.inserir(livro);
            }
        } else {
            System.out.println("Não há nenhuma alteração para ser desfeita.");
        }
    }

    private void salvarAlteracoesNoBanco() {
        conn.salvarAlteracoes(lista);
        conn.salvarAlteracoes(fila);
    }

}
