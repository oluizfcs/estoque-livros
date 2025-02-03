package com.ed1.estoque.ordenacao;

import com.ed1.estoque.Livro;

/**
 *
 * @author luiz
 */
public class BubbleSort {

    Livro[] livros;

    public BubbleSort(Livro[] livros) {
        this.livros = livros;
    }

    public Livro[] ordenar() {
        for (int j = 0; j < livros.length; j++) {

            Boolean trocou = false;

            for (int i = 0; i < livros.length - 1; i++) {
               
                if (livros[i].getTitulo().compareToIgnoreCase(livros[i + 1].getTitulo()) > 0) {
                    trocar(i);
                    trocou = true;
                }
            }

            if (!trocou) {
                break;
            }
        }
        
        return livros;
    }

    /**
     * Troca o valor da posição atual com a próxima
     *
     * @param position
     */
    private void trocar(int position) {
        Livro temporario = livros[position];

        livros[position] = livros[position + 1];
        livros[position + 1] = temporario;
    }

}
