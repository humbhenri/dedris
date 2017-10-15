package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 * Representa um grid do jogo, onde cada elemento do array é um número que indica a sua cor.
 * O array está invertido, i.e., o elemento (i, j) está na linha j e coluna i, para ficar mais
 * fácil de ser desenhado pelo android.
 */

class Grid {
    static final int ALTURA = 20;
    static final int LARGURA = 10;

    // usa dois grids para facilitar a implementação de limpar antes do tetramino ser movido
    final int[][] grid; // esse terá somente o grid e as peças já consolidadas
    private final int[][] gridComTetramino; // esse terá o grid com o tetramino atual

    private int tetraminoAlturaAtual; // coordenada vertical a partir da qual o tetramino será desenhado
    private int tetraminoInicioAtual; // coordenada horizontal a partir da qual o tetramino será desenhado
    private Tetramino tetraminoAtual;
    private CriadorTetramino criadorTetramino;
    private GridListener listener;

    Grid(CriadorTetramino criadorTetramino, GridListener listener) {
        this.criadorTetramino = criadorTetramino;
        this.listener = listener;
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
        ArrayUtils.copia(gridComTetramino, grid);
    }

    int get(int i, int j) {
        return gridComTetramino[i][j];
    }

    void rotacionaTetramino() {
        if (tetraminoAtual != null) {
            tetraminoAtual = tetraminoAtual.rotaciona();

            // faz o tetramino caber inteiro na tela ao rotacionar na base
            int dif = ALTURA - (tetraminoAlturaAtual + tetraminoAtual.altura());
            if (dif < 0) {
                tetraminoAlturaAtual += dif;
            }

            tetraminoInicioAtual = tetraminoAtual.inicio();
            move();
        }
    }

    void moveBaixo() {
        if (tetraminoEncostaBlocos() && tetraminoAlturaAtual == 0) {
            listener.jogoAcabou();
            return;
        } else if (tetraminoEncostaPiso() || tetraminoEncostaBlocos()) {
            tetraminoGruda();
        } else {
            tetraminoAlturaAtual++;
        }
        move();
    }

    void moveEsquerda() {
        // TODO detectar se vai encostar num bloco primeiro
        // FIXME ignorar colunas zeradas
        if (tetraminoAtual != null && tetraminoInicioAtual > 0) {
            tetraminoInicioAtual--;
            move();
        }
    }

    void moveDireita() {
        // TODO detectar se vai encostar num bloco primeiro
        if (tetraminoAtual != null && tetraminoInicioAtual + tetraminoAtual.largura() < LARGURA) {
            tetraminoInicioAtual++;
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
                // +1 na altura porque eu quero detectar se o próximo movimento p/ baixo vai encostar
                if (tetraminoAtual.grid[i][j] != 0 && grid[tetraminoInicioAtual + i][tetraminoAlturaAtual + 1 + j] != 0)
                    return true;
            }
        }
        return false;
    }

    private void tetraminoGruda() {
        ArrayUtils.copia(grid, gridComTetramino);
        inicia();
        listener.tetraminoGrudou();
        removeLinhas();
    }

    void removeLinhas() {
        for (int linhaAtual = ALTURA - 1; linhaAtual >= 0; linhaAtual--) {
            boolean preenchido = true;
            for (int j = 0; j < LARGURA; j++) {
                preenchido &= grid[j][linhaAtual] != 0;
            }
            if (preenchido) {
                listener.removeuLinha(linhaAtual);
                ArrayUtils.removeColuna(grid, linhaAtual);
            }
        }
    }

    private void inicia() {
        tetraminoAtual = criadorTetramino.proximo();
        tetraminoAlturaAtual = 0;
        tetraminoInicioAtual = 0;
    }

    int getTetraminoAlturaAtual() {
        return tetraminoAlturaAtual;
    }

    int getTetraminoInicioAtual() {
        return tetraminoInicioAtual;
    }

}
