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

    public static void removeColuna(int[][] array, int coluna) {
        for (int i = coluna; i > 0; i--) {
            for (int k = 0; k < array.length; k++) {
                array[k][i] = array[k][i - 1];
            }
        }
        for (int i = 0; i < array.length; i++) {
            array[i][0] = 0;
        }
    }

    /**
     * Retorna a primeira linha do array 2D que tem um número diferente de zero.
     *
     * @param array array intxint
     * @return coluna 0 .. array.lenght - 1 ou array.length se o array só tem zeros
     */
    public static int primeiraLinhaNaoNula(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (array[i][j] != 0) {
                    return i;
                }
            }
        }

        return array.length; // array só tem zeros
    }
}
