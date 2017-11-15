package com.example.humbhenri.dedris;

import com.example.humbhenri.dedris.eventos.EventoGameOver;
import com.example.humbhenri.dedris.eventos.EventoLinhaRemovida;
import com.example.humbhenri.dedris.eventos.EventoTetraminoGrudou;

import org.greenrobot.eventbus.EventBus;

/**
 * Created by humbhenri on 12/10/17.
 * Representa um grid do jogo, onde cada elemento do array é um número que indica a sua cor.
 * O array está invertido, i.e., o elemento (i, j) está na linha j e coluna i, para ficar mais
 * fácil de ser desenhado pelo android.
 */

class Grid {
    static final int ALTURA = 20;
    static final int LARGURA = 10;

    public enum Direcao {
        PARA_BAIXO, ESQUERDA, DIREITA
    }

    // usa dois grids para facilitar a implementação de limpar antes do tetramino ser movido
    final int[][] grid; // esse terá somente o grid e as peças já consolidadas
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

            // faz o tetramino caber inteiro ao rotacionar no lado
            tetraminoInicioAtual = Math.max(0, tetraminoInicioAtual);
            move();
        }
    }

    void moveBaixo() {
        boolean encostaBlocos = tetraminoEncostaBlocos(Direcao.PARA_BAIXO);
        if (encostaBlocos && tetraminoAlturaAtual == 0) {
            EventBus.getDefault().post(new EventoGameOver());
            return;
        } else if (tetraminoEncostaPiso() || encostaBlocos) {
            tetraminoGruda();
        } else {
            tetraminoAlturaAtual++;
        }
        move();
    }

    void moveEsquerda() {
        if (tetraminoAtual != null
                && tetraminoInicioAtual + tetraminoAtual.inicio() > 0
                && !tetraminoEncostaBlocos(Direcao.ESQUERDA)) {
            tetraminoInicioAtual--;
            move();
        }
    }

    void moveDireita() {
        if (tetraminoAtual != null
                && tetraminoInicioAtual + tetraminoAtual.largura() < LARGURA
                && !tetraminoEncostaBlocos(Direcao.DIREITA)) {
            tetraminoInicioAtual++;
            move();
        }
    }

    private boolean tetraminoEncostaBlocos(Direcao direcao) {
        for (int i = 0; i < tetraminoAtual.grid.length; i++) {
            for (int j = 0; j < tetraminoAtual.grid[0].length; j++) {
                switch (direcao) {
                    case PARA_BAIXO:
                        if (tetraminoAtual.grid[i][j] != 0 && grid[tetraminoInicioAtual + i][tetraminoAlturaAtual + 1 + j] != 0) {
                            return true;
                        }
                        break;
                    case ESQUERDA:
                        if (tetraminoAtual.grid[i][j] != 0 && grid[tetraminoInicioAtual + i - 1][tetraminoAlturaAtual + j] != 0) {
                            return true;
                        }
                        break;
                    case DIREITA:
                        if (tetraminoAtual.grid[i][j] != 0 && grid[tetraminoInicioAtual + i + 1][tetraminoAlturaAtual + j] != 0) {
                            return true;
                        }
                        break;
                }
            }
        }
        return false;
    }

    private void move() {
        limpa();
        merge();
    }

    private boolean tetraminoEncostaPiso() {
        return tetraminoAlturaAtual + tetraminoAtual.altura() == ALTURA;
    }

    private void tetraminoGruda() {
        ArrayUtils.copia(grid, gridComTetramino);
        inicia();
        EventBus.getDefault().post(new EventoTetraminoGrudou());
        removeLinhas();
    }

    void removeLinhas() {
        int linhasRemovidas = 0;
        for (int linhaAtual = ALTURA - 1; linhaAtual >= 0; linhaAtual--) {
            boolean preenchido = true;
            for (int j = 0; j < LARGURA; j++) {
                preenchido &= grid[j][linhaAtual] != 0;
            }
            if (preenchido) {
                linhasRemovidas++;
                ArrayUtils.removeColuna(grid, linhaAtual);
            }
        }
        if (linhasRemovidas > 0) {
            EventBus.getDefault().post(new EventoLinhaRemovida(linhasRemovidas));
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
