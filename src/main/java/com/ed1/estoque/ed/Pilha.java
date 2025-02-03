package com.ed1.estoque.ed;

/**
 *
 * @author luiz
 */
public class Pilha {

    private Object[][] pilha;

    public Object[][] getpilha() {
        return pilha;
    }

    public void setpilha(Object[][] pilha) {
        this.pilha = pilha;
    }

    public Pilha() {
        pilha = new Object[0][];
    }

    /**
     * ação:<br> 
     * 1 - inserir<br> 
     * 2 - remover
     *
     * @param livroAcao {Livro, acao}
     */
    public void inserir(Object[] livroAcao) {

        aumentarCapacidade();

        pilha[pilha.length - 1] = livroAcao;
    }

    public Object[] remover() {

        Object[] saiu = null;

        if (pilha.length != 0) {

            saiu = pilha[pilha.length - 1];
            pilha[pilha.length - 1] = null;

            diminuirCapacidade();

        } else {
            System.out.println("A pilha está vazia");
        }

        return saiu;
    }

    private void aumentarCapacidade() {

        Object[][] novaPilha = new Object[pilha.length + 1][];

        for (int i = 0; i < this.pilha.length; i++) {
            novaPilha[i] = pilha[i];
        }

        pilha = novaPilha;
    }

    private void diminuirCapacidade() {

        Object[][] novaPilha = new Object[pilha.length - 1][];

        for (int i = 0; i < pilha.length - 1; i++) {
            novaPilha[i] = pilha[i];
        }

        pilha = novaPilha;
    }

    public void listar() {
        if (pilha.length != 0) {
            for (Object[] livroAcao : this.pilha) {
                System.out.println(livroAcao[0] + " - " + livroAcao[1]);
            }
        } else {
            System.out.println("A pilha está vazia");
        }
    }
}
