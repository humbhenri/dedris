package com.example.humbhenri.dedris;

/**
 * Created by humbhenri on 12/10/17.
 */
enum Tetramino {
    RETA {
        public int[][] grid() {
            return new int[][]{
                    {1, 0, 0},
                    {1, 0, 0},
                    {1, 0, 0},
                    {1, 0, 0},
            };
        }
    },
    QUADRADO {
        public int[][] grid() {
            return new int[][]{
                    {2, 2, 0},
                    {2, 2, 0},
                    {0, 0, 0},
                    {0, 0, 0},
            };
        }
    },
    T {
        public int[][] grid() {
            return new int[][]{
                    {3, 0, 0},
                    {3, 3, 0},
                    {3, 0, 0},
                    {0, 0, 0},
            };
        }
    },
    J {
        public int[][] grid() {
            return new int[][]{
                    {4, 0, 0},
                    {4, 0, 0},
                    {4, 4, 0},
                    {0, 0, 0},
            };
        }
    },
    L {
        public int[][] grid() {
            return new int[][]{
                    {5, 5, 0},
                    {5, 0, 0},
                    {5, 0, 0},
                    {0, 0, 0},
            };
        }
    },
    S {
        public int[][] grid() {
            return new int[][]{
                    {0, 6, 0},
                    {6, 6, 0},
                    {6, 0, 0},
                    {0, 0, 0},
            };
        }
    },
    Z {
        public int[][] grid() {
            return new int[][]{
                    {7, 0, 0},
                    {7, 7, 0},
                    {0, 7, 0},
                    {0, 0, 0},
            };
        }
    },;

    public int[][] grid() {
        return null;
    }
}
