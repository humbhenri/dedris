package com.example.humbhenri.dedris;

import android.support.annotation.NonNull;

import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;

/**
 * Created by humbhenri on 13/10/17.
 */
@RunWith(Parameterized.class)
public class GridTest {

    private Tetramino tetraminoAtual;

    @Parameterized.Parameters(name = "{0}")
    public static Iterable<Tetramino> dados() {
        return Arrays.asList(Tetramino.T(), Tetramino.J(), Tetramino.L(), Tetramino.QUADRADO(), Tetramino.RETA(),
                Tetramino.S(), Tetramino.Z());
    }

    public GridTest(Tetramino tetraminoAtual) {
        this.tetraminoAtual = tetraminoAtual;
    }

    @Test
    public void moveBaixo() {
        Grid grid = new Grid(getCriadorTetramino(), getListener());
        grid.moveBaixo();
        while (grid.getTetraminoAlturaAtual() > 0) grid.moveBaixo();
        Assert.assertEquals(0, grid.getTetraminoAlturaAtual());
    }

    @Test
    public void moveParaLado() {
        Grid grid = new Grid(getCriadorTetramino(), getListener());
        for (int i = 0; i < 100; i++)
            grid.moveEsquerda();
        Assert.assertEquals(0, grid.getTetraminoInicioAtual());
        for (int i = 0; i < 100; i++)
            grid.moveDireita();
        Assert.assertEquals(Grid.LARGURA - tetraminoAtual.largura(), grid.getTetraminoInicioAtual());
    }

    @Test
    public void rotaciona() {
        Grid grid = new Grid(getCriadorTetramino(), getListener());
        for (int i = 0; i < Grid.ALTURA; i++)
            grid.moveBaixo();
        grid.rotacionaTetramino();
    }

    @NonNull
    private CriadorTetramino getCriadorTetramino() {
        return new CriadorTetramino() {
            @Override
            public Tetramino proximo() {
                return tetraminoAtual;
            }
        };
    }

    @NonNull
    private GridListener getListener() {
        return new GridListener() {
            @Override
            public void tetraminoGrudou() {

            }

            @Override
            public void jogoAcabou() {

            }
        };
    }

}