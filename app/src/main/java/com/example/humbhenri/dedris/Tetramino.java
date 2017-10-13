package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */
enum Tetramino {
    RETA,
    QUADRADO,
    T,
    J,
    L,
    S,
    Z;

    public int[][] grid() {
        switch (this) {
            case RETA:
                return new int[][]{
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                        {1, 0, 0, 0},
                };
            case J:
                return new int[][]{
                        {4, 0, 0, 0},
                        {4, 0, 0, 0},
                        {4, 4, 0, 0},
                        {0, 0, 0, 0},
                };
            case L:
                return new int[][]{
                        {5, 5, 0, 0},
                        {5, 0, 0, 0},
                        {5, 0, 0, 0},
                        {0, 0, 0, 0},
                };
            case QUADRADO:
                return new int[][]{
                        {2, 2, 0, 0},
                        {2, 2, 0, 0},
                        {0, 0, 0, 0},
                        {0, 0, 0, 0},
                };
            case S:
                return new int[][]{
                        {0, 6, 0, 0},
                        {6, 6, 0, 0},
                        {6, 0, 0, 0},
                        {0, 0, 0, 0},
                };
            case T:
                return new int[][]{
                        {3, 0, 0, 0},
                        {3, 3, 0, 0},
                        {3, 0, 0, 0},
                        {0, 0, 0, 0},
                };
            case Z:
                return new int[][]{
                        {7, 0, 0, 0},
                        {7, 7, 0, 0},
                        {0, 7, 0, 0},
                        {0, 0, 0, 0},
                };
            default:
                return null;
        }
    }

    /*
     * Altura deve ser a maior coluna com um número diferente de zero
     */
    public int altura() {
        int[][] grid = this.grid();
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
}
