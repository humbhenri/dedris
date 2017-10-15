package com.example.humbhenri.dedris;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.util.Random;

/**
 * Created by humbhenri on 13/10/17.
 */

public class CriadorTetraminoAleatorio implements CriadorTetramino {

    private final String[] tetraminos = new String[]{"RETA", "QUADRADO", "T", "J", "L", "S", "Z"};

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Tetramino proximo() {
        Random r = new Random();
        return CriadorTetraminoReflexivo.cria(tetraminos[r.nextInt(tetraminos.length)]);
    }
}
