package com.example.humbhenri.dedris;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.Log;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by humbhenri on 14/10/17.
 */

class CriadorTetraminoReflexivo {
    private static final String TAG = CriadorTetraminoReflexivo.class.getName();
    private static Map<String, Method> criadores;

    static {
        String[] tetraminos = new String[]{"RETA", "QUADRADO", "T", "J", "L", "S", "Z"};
        criadores = new HashMap<>();
        for (String tetramino : tetraminos) {
            try {
                criadores.put(tetramino, Tetramino.class.getMethod(tetramino));
            } catch (NoSuchMethodException e) {
                Log.e(TAG, e.getMessage());
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    static Tetramino cria(String nome) {
        try {
            return (Tetramino) criadores.get(nome).invoke(null);
        } catch (IllegalAccessException | InvocationTargetException e) {
            Log.e(TAG, e.getMessage());
        }
        return null;
    }
}
