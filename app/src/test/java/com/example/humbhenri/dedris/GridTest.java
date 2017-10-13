package com.example.humbhenri.dedris;

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

    @Parameterized.Parameters
    public static Iterable<Tetramino> dados() {
        return Arrays.asList(Tetramino.values());
    }

    public GridTest(Tetramino tetraminoAtual) {
        this.tetraminoAtual = tetraminoAtual;
    }

    @Test
    public void moveBaixo() {
        Grid grid = new Grid();
        grid.coloca(tetraminoAtual);
        for (int i = 0; i < 100; i++)
            grid.moveBaixo();
        Assert.assertEquals(Grid.ALTURA - tetraminoAtual.altura(), grid.getAlturaAtual());
    }

}