package com.example.humbhenri.dedris;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Random;

/**
 * Created by humbhenri on 13/10/17.
 */

public class CriadorTetraminoAleatorio implements CriadorTetramino {

    private static Method[] criadores;

    static {
        String[] tetraminos = new String[]{"RETA", "QUADRADO", "T", "J", "L", "S", "Z"};
        criadores = new Method[tetraminos.length];
        for (int i = 0; i < tetraminos.length; i++) {
            try {
                criadores[i] = Tetramino.class.getMethod(tetraminos[i]);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
                // nunca acontece
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    @Override
    public Tetramino proximo() {
        Random r = new Random();
        try {
            return (Tetramino) criadores[r.nextInt(criadores.length)].invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
            // nunca acontece
        }
        return null;
    }
}
