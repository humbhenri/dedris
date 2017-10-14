package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */

public class Grid {
    public static final int ALTURA = 20;
    public static final int LARGURA = 10;

    // usa dois grids para facilitar a implementação de limpar antes do tetramino ser movido
    private final int[][] grid; // esse terá somente o grid e as peças já consolidadas
    private final int[][] gridComTetramino; // esse terá o grid com o tetramino atual

    private int tetraminoAlturaAtual; // coordenada vertical a partir da qual o tetramino será desenhado
    private int tetraminoInicioAtual; // coordenada horizontal a partir da qual o tetramino será desenhado
    private Tetramino tetraminoAtual;
    private CriadorTetramino criadorTetramino;

    public Grid(CriadorTetramino criadorTetramino) {
        this.criadorTetramino = criadorTetramino;
        grid = new int[LARGURA + 1][ALTURA + 1]; // altura tem uma linha a mais para evitar ArrayIndexOutOfBoundExceptions com retas
        gridComTetramino = new int[LARGURA + 1][ALTURA + 1];
        inicia();
    }

    private void merge() {
        int[][] gridTetramino = tetraminoAtual.grid;
        for (int i = 0; i < gridTetramino.length; i++) {
            for (int j = 0; j < tetraminoAtual.altura(); j++) {
                if (gridTetramino[i][j] != 0)
                    gridComTetramino[i + tetraminoInicioAtual][j + tetraminoAlturaAtual] = gridTetramino[i][j];
            }
        }
    }

    private void limpa() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                gridComTetramino[i][j] = grid[i][j];
            }
        }
        ArrayUtils.copia(gridComTetramino, grid);
    }

    public int get(int i, int j) {
        return gridComTetramino[i][j];
    }

    public void rotacionaTetramino() {
        if (tetraminoAtual != null) {
            tetraminoAtual = tetraminoAtual.rotaciona();

            // faz o tetramino caber inteiro na tela ao rotacionar na base
            int dif = ALTURA - (tetraminoAlturaAtual + tetraminoAtual.altura());
            if (dif < 0) {
                tetraminoAlturaAtual += dif;
            }

            move();
        }
    }

    public void moveBaixo() {
        if (tetraminoEncostaPiso() || tetraminoEncostaBlocos())
            tetraminoGruda();
        else
            tetraminoAlturaAtual++;
        move();
    }

    public void moveEsquerda() {
        if (tetraminoAtual != null) {
            if (tetraminoInicioAtual > 0) tetraminoInicioAtual--;
            move();
        }
    }

    public void moveDireita() {
        if (tetraminoAtual != null) {
            if (tetraminoInicioAtual + tetraminoAtual.largura() < LARGURA) tetraminoInicioAtual++;
            move();
        }
    }

    private void move() {
        limpa();
        merge();
    }

    private boolean tetraminoEncostaPiso() {
        return tetraminoAlturaAtual + tetraminoAtual.altura() == ALTURA;
    }

    private boolean tetraminoEncostaBlocos() {
        for (int i = 0; i < tetraminoAtual.grid.length; i++) {
            for (int j = 0; j < tetraminoAtual.grid[0].length; j++) {
                // +1 na altura porque eu quero detectar que o próximo movimento p/ baixo vai encostar
                if (tetraminoAtual.grid[i][j] != 0 && grid[tetraminoInicioAtual + i][tetraminoAlturaAtual + 1 + j] != 0)
                    return true;
            }
        }

        return false;
    }

    private void tetraminoGruda() {
        ArrayUtils.copia(grid, gridComTetramino);
        inicia();
    }

    private void inicia() {
        tetraminoAtual = criadorTetramino.proximo();
        tetraminoAlturaAtual = 0;
        tetraminoInicioAtual = 0;
    }

    public void coloca(Tetramino tetramino) {
        tetraminoAtual = tetramino;
        tetraminoAlturaAtual = 0;
        merge();
    }

    public int getTetraminoAlturaAtual() {
        return tetraminoAlturaAtual;
    }

    public int getTetraminoInicioAtual() {
        return tetraminoInicioAtual;
    }

}
