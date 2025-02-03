package com.ed1.estoque.ed;

/**
 *
 * @author luiz
 */
public class Fila {

    private String[] fila;

    public String[] getFila() {
        return fila;
    }

    public void setFila(String[] fila) {
        this.fila = fila;
    }

    public Fila() {
        fila = new String[0];
    }

    public void inserir(String pessoa) {

        aumentarCapacidade();

        fila[fila.length - 1] = pessoa;
    }

    public String remover() {

        String saiu = null;

        if (fila.length != 0) {

            saiu = fila[0];
            fila[0] = null;

            // faz as pessoas da fila avançarem uma posição
            for (int i = 0; i < fila.length - 1; i++) {
                fila[i] = fila[i + 1];
            }

            diminuirCapacidade();
        } else {
            System.out.println("A fila está vazia");
        }

        return saiu;
    }

    private void aumentarCapacidade() {

        String[] novaFila = new String[fila.length + 1];

        for (int i = 0; i < this.fila.length; i++) {
            novaFila[i] = fila[i];
        }

        fila = novaFila;
    }

    private void diminuirCapacidade() {

        String[] novaFila = new String[fila.length - 1];

        for (int i = 0; i < fila.length - 1; i++) {
            novaFila[i] = fila[i];
        }

        fila = novaFila;
    }

    public void listar() {
        if (fila.length != 0) {
            for (String pessoa : this.fila) {
                System.out.print(pessoa + ", ");
            }
            System.out.println("");
        } else {
            System.out.println("A fila está vazia");
        }
    }
}
