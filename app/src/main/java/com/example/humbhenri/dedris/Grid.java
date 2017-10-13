package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */

public class Grid {
    public static final int ALTURA = 20;
    public static final int LARGURA = 10;
    private final int[][] grid; // esse terá somente o grid e as peças já consolidadas
    private final int[][] gridComTetramino; // esse terá o grid com o tetramino atual
    private int alturaAtual;
    private Tetramino tetraminoAtual;

    public Grid() {
        grid = new int[LARGURA][ALTURA + 1]; // uma linha a mais para evitar ArrayIndexOutOfBoundExceptions
        gridComTetramino = new int[LARGURA][ALTURA + 1];
        tetraminoAtual = Tetramino.Z;
        alturaAtual = 0;
        merge();
    }

    private void merge() {
        int[][] gridTetramino = tetraminoAtual.grid();
        for (int i=0; i<gridTetramino.length; i++) {
            for (int j=0; j<gridTetramino[i].length; j++) {
                gridComTetramino[i][j + alturaAtual] = gridTetramino[i][j];
            }
        }
    }

    public int get(int i, int j) {
        return gridComTetramino[i][j];
    }

    public void moveBaixo() {
        if (alturaAtual < ALTURA-2) alturaAtual++;
        System.out.println(alturaAtual);
        limpa();
        merge();
    }

    private void limpa() {
        for (int i=0; i<grid.length; i++) {
            for (int j=0; j<grid[0].length; j++) {
                gridComTetramino[i][j] = grid[i][j];
            }
        }
    }
}
