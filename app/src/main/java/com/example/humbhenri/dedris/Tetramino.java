package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */
enum Tetramino {
    RETA(new int[][]{
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
            {1, 0, 0, 0},
    }),
    QUADRADO(new int[][]{
            {2, 2, 0, 0},
            {2, 2, 0, 0},
            {0, 0, 0, 0},
            {0, 0, 0, 0},
    }),
    T(new int[][]{
            {3, 0, 0, 0},
            {3, 3, 0, 0},
            {3, 0, 0, 0},
            {0, 0, 0, 0},
    }),
    J(new int[][]{
            {4, 0, 0, 0},
            {4, 0, 0, 0},
            {4, 4, 0, 0},
            {0, 0, 0, 0},
    }),
    L(new int[][]{
            {5, 5, 0, 0},
            {5, 0, 0, 0},
            {5, 0, 0, 0},
            {0, 0, 0, 0},
    }),
    S(new int[][]{
            {0, 6, 0, 0},
            {6, 6, 0, 0},
            {6, 0, 0, 0},
            {0, 0, 0, 0},
    }),
    Z(new int[][]{
            {7, 0, 0, 0},
            {7, 7, 0, 0},
            {0, 7, 0, 0},
            {0, 0, 0, 0},
    });

    public static final int TAMANHO_GRID = 4;

    int[][] grid;

    Tetramino(int[][] grid) {
        this.grid = grid;
    }

    /*
     * Altura deve ser a maior coluna com um número diferente de zero
     */
    public int altura() {
        int altura = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    altura = Math.max(altura, j);
                }
            }
        }
        return altura + 1; // adiciona um porque índice no array começa de zero
    }

    public void rotaciona() {
        int[][] novo = new int[TAMANHO_GRID][TAMANHO_GRID];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                novo[i][j] = grid[TAMANHO_GRID - j - 1][i];
            }
        }
        grid = novo;
    }
}
