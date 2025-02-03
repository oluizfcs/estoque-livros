package com.ed1.estoque.ordenacao;

import com.ed1.estoque.Livro;

/**
 *
 * @author luiz
 */
public class SelectionSort {

    private Livro[] livros;
    
    public SelectionSort(Livro[] livros) {
        this.livros = livros;
    }

    public Livro[] ordenar() {

        for (int j = 0; j < livros.length - 2; j++) {

            int menor = j;

            for (int i = j + 1; i < livros.length - 1; i++) {

                if (livros[i].getAnoDePublicacao() < livros[menor].getAnoDePublicacao()) {
                    menor = i;
                }
            }

            if (menor != j) {
                trocar(menor, j);
            }
        }
        
        return livros;
    }

    /**
     * Troca o valor da posição de origem com a de destino
     *
     * @param position
     */
    private void trocar(int origem, int destino) {
        Livro temporario = livros[destino];

        livros[destino] = livros[origem];
        livros[origem] = temporario;
    }
}
