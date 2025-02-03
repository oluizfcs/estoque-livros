package com.ed1.estoque.ed;

import com.ed1.estoque.Livro;

/**
 *
 * @author luiz
 */
public class Lista {

    private Livro[] lista;

    public Livro[] getLivros() {
        return lista;
    }

    public void setLista(Livro[] lista) {
        this.lista = lista;
    }

    public Lista() {
        this.lista = new Livro[0];
    }

    public void inserir(Livro obj) {

        aumentarCapacidade();

        this.lista[this.lista.length - 1] = obj;
    }

    public void remover(int id) {
        
        int pos = procurarPosicaoPeloId(id);
        
        try {

            if (pos != -1) {
                this.lista[pos] = null;

                // coloca o próximo elemento no lugar do que foi removido e o próximo do próximo no lugar do próximo e assim por diante
                for (int i = pos; i < this.lista.length - 1; i++) {
                    this.lista[i] = this.lista[i + 1];
                }

                diminuirCapacidade();
            }

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Não existe posição " + pos + ".");
        }
    }

    private void aumentarCapacidade() {

        Livro[] novaLista = new Livro[this.lista.length + 1];

        for (int i = 0; i < this.lista.length; i++) {
            novaLista[i] = this.lista[i];
        }

        this.lista = novaLista;
    }

    private void diminuirCapacidade() {

        Livro[] novaLista = new Livro[this.lista.length - 1];

        for (int i = 0; i < this.lista.length - 1; i++) {
            novaLista[i] = this.lista[i];
        }

        this.lista = novaLista;
    }

    private int procurarPosicaoPeloId(int id) {

    Livro[] livros = this.getLivros();

    int pos = -1;

    for (int i = 0; i < this.lista.length; i++) {
        if (livros[i].getId() == id) {
            pos = i;
        }
    }

    return pos;
    }
   
}
