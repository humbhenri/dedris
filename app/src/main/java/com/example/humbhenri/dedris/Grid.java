package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */

public class Grid {
    public static final int ALTURA = 20;
    public static final int LARGURA = 10;
    private final int[][] grid; // esse terá somente o grid e as peças já consolidadas
    private final int[][] gridComTetramino; // esse terá o grid com o tetramino atual
    private int tetraminoAlturaAtual;
    private int tetraminoInicioAtual;
    private Tetramino tetraminoAtual;

    public Grid() {
        grid = new int[LARGURA + 1][ALTURA + 1]; // altura tem uma linha a mais para evitar ArrayIndexOutOfBoundExceptions com retas
        gridComTetramino = new int[LARGURA + 1][ALTURA + 1];
    }

    private void merge() {
        int[][] gridTetramino = tetraminoAtual.grid;
        for (int i=0; i<gridTetramino.length; i++) {
            for (int j = 0; j < tetraminoAtual.altura(); j++) {
                gridComTetramino[i + tetraminoInicioAtual][j + tetraminoAlturaAtual] = gridTetramino[i][j];
            }
        }
    }

    public int get(int i, int j) {
        return gridComTetramino[i][j];
    }

    public void moveBaixo() {
        if (tetraminoAlturaAtual < ALTURA - tetraminoAtual.altura()) tetraminoAlturaAtual++;
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

    public void coloca(Tetramino tetramino) {
        tetraminoAtual = tetramino;
        tetraminoAlturaAtual = 0;
        merge();
    }

    public int getTetraminoAlturaAtual() {
        return tetraminoAlturaAtual;
    }

    public void rotacionaTetramino() {
        if (tetraminoAtual != null) {
            limpa();
            tetraminoAtual.rotaciona();
            merge();
        }
    }

    public void moveEsquerda() {
        if (tetraminoAtual != null) {
            if (tetraminoInicioAtual > 0) tetraminoInicioAtual--;
            limpa();
            merge();
        }
    }

    public void moveDireita() {
        if (tetraminoAtual != null) {
            if (tetraminoInicioAtual + tetraminoAtual.largura() < LARGURA) tetraminoInicioAtual++;
            limpa();
            merge();
        }
    }

    public int getTetraminoInicioAtual() {
        return tetraminoInicioAtual;
    }
}
