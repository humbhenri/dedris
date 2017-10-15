package com.example.humbhenri.dedris;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Created by humbhenri on 14/10/17.
 */

public class GridTestSemParametros {

    @Test
    public void removeLinhas() {
        Grid grid = new Grid(getCriadorTetramino(), getListener());

        // preenche linha mais baixa
        for (int i = 0; i < Grid.LARGURA; i++) {
            grid.grid[i][Grid.ALTURA - 1] = 1;
        }

        // coloca um quadrado na segunda linha mais baixa
        grid.grid[0][Grid.ALTURA - 2] = 1;

        // remove a linha
        grid.removeLinhas();

        // assegura que a linha foi removida e que a linha de cima desceu
        for (int i = 1; i < Grid.LARGURA; i++) {
            Assert.assertEquals(0, grid.grid[i][Grid.ALTURA - 1]);
        }
        Assert.assertEquals(1, grid.grid[0][Grid.ALTURA - 1]);
    }

    private GridListener getListener() {
        return new GridListener() {
            @Override
            public void tetraminoGrudou() {

            }

            @Override
            public void jogoAcabou() {

            }

            @Override
            public void removeuLinha(int linha) {

            }
        };
    }

    private CriadorTetramino getCriadorTetramino() {
        return new CriadorTetramino() {
            @Override
            public Tetramino proximo() {
                return Tetramino.RETA();
            }
        };
    }

}
