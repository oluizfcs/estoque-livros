package com.ed1.estoque;

/**
 *
 * @author luiz
 */
public class Lista {

    private Object[] lista;

    public Object[] getLista() {
        return lista;
    }

    public void setLista(Object[] lista) {
        this.lista = lista;
    }

    Lista() {
        this.lista = new Object[0];
    }

    public void inserir(Object obj) {

        aumentarCapacidade();

        this.lista[this.lista.length - 1] = obj;
    }

    public void remover(int pos) {
        try {

            this.lista[pos] = null;

            // coloca o próximo elemento no lugar do que foi removido e o próximo do próximo no lugar do próximo e assim por diante
            for (int i = pos; i < this.lista.length - 1; i++) {
                this.lista[i] = this.lista[i + 1];
            }

            diminuirCapacidade();

        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Não existe posição " + pos + ".");
        }
    }

    private void aumentarCapacidade() {

        Object[] novaLista = new Object[this.lista.length + 1];

        for (int i = 0; i < this.lista.length; i++) {
            novaLista[i] = this.lista[i];
        }

        this.lista = novaLista;
    }

    private void diminuirCapacidade() {

        Object[] novaLista = new Object[this.lista.length - 1];

        for (int i = 0; i < this.lista.length - 1; i++) {
            novaLista[i] = this.lista[i];
        }

        this.lista = novaLista;
    }
    
}
