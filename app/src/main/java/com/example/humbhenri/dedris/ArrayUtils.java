package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 13/10/17.
 */

public class ArrayUtils {
    public static void copia(int[][] destino, int[][] origem) {
        for (int i = 0; i < origem.length; i++) {
            for (int j = 0; j < origem[i].length; j++) {
                destino[i][j] = origem[i][j];
            }
        }
    }
}
