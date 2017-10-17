package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */
class Tetramino {
    private final String nome;
    int[][] grid;

    public static Tetramino RETA() {
        return new Tetramino(new int[][]{
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
                {1, 0, 0, 0},
        }, "RETA");
    }

    public static Tetramino QUADRADO() {
        return new Tetramino(new int[][]{
                {2, 2},
                {2, 2},
        }, "QUADRADO");
    }

    public static Tetramino T() {
        return new Tetramino(new int[][]{
                {3, 0, 0, 0},
                {3, 3, 0, 0},
                {3, 0, 0, 0},
                {0, 0, 0, 0},
        }, "T");
    }

    public static Tetramino J() {
        return new Tetramino(new int[][]{
                {4, 0, 0, 0},
                {4, 0, 0, 0},
                {4, 4, 0, 0},
                {0, 0, 0, 0},
        }, "J");
    }

    public static Tetramino L() {
        return new Tetramino(new int[][]{
                {5, 5, 0, 0},
                {5, 0, 0, 0},
                {5, 0, 0, 0},
                {0, 0, 0, 0},
        }, "L");
    }

    public static Tetramino S() {
        return new Tetramino(new int[][]{
                {0, 6, 0, 0},
                {6, 6, 0, 0},
                {6, 0, 0, 0},
                {0, 0, 0, 0},
        }, "S");
    }

    public static Tetramino Z() {
        return new Tetramino(new int[][]{
                {7, 0, 0, 0},
                {7, 7, 0, 0},
                {0, 7, 0, 0},
                {0, 0, 0, 0},
        }, "Z");
    }


    private Tetramino(int[][] grid, String nome) {
        this.grid = grid;
        this.nome = nome;
    }

    /*
     * Altura deve ser a maior coluna com um número diferente de zero
     */
    public int altura() {
        int altura = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] != 0) {
                    altura = Math.max(altura, j);
                }
            }
        }
        return altura + 1; // adiciona um porque índice no array começa de zero
    }

    /*
    * Deve ser imutável para facilitar testes
    */
    public Tetramino rotaciona() {
        int[][] novo = new int[grid.length][grid[0].length];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                novo[i][j] = grid[grid.length - j - 1][i];
            }
        }
        return new Tetramino(novo, nome);
    }

    /*
    *  largura é igual à maior linha que contém um dígito diferente de zero
    */
    public int largura() {
        int largura = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j] != 0) {
                    largura = Math.max(i, largura);
                }
            }
        }
        return largura + 1;
    }

    @Override
    public String toString() {
        return nome;
    }

    public int inicio() {
        return ArrayUtils.primeiraLinhaNaoNula(this.grid);
    }
}
