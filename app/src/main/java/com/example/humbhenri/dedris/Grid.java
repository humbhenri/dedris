package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */

public class Grid {
    public static final int ALTURA = 20;
    public static final int LARGURA = 10;
    private final int[][] grid;

    public Grid() {
        grid = new int[LARGURA][ALTURA];
        merge(grid, Tetramino.Z);
    }

    private void merge(int[][] grid, Tetramino tetramino) {
        int[][] gridTetramino = tetramino.grid();
        for (int i=0; i<gridTetramino.length; i++) {
            for (int j=0; j<gridTetramino[0].length; j++) {
                grid[i][j] = gridTetramino[i][j];
            }
        }
    }

    public int get(int i, int j) {
        return grid[i][j];
    }

}
