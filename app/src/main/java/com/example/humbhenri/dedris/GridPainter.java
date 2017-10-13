package com.example.humbhenri.dedris;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by humbhenri on 12/10/17.
 */

public class GridPainter {

    private final Grid grid;
    private final Tela tela;
    private final int tamanho;

    public GridPainter(Grid grid, Tela tela) {
        this.grid = grid;
        this.tela = tela;
        this.tamanho = (int) Math.floor((float) tela.altura() / (float) Grid.ALTURA);
    }

    public void draw(Canvas canvas) {
        int larguraPx = tamanho * Grid.LARGURA;
        int alturaPx = larguraPx * 2;
        int inicio = tela.largura() / Grid.LARGURA;
        Paint texto = new Paint();
        texto.setColor(Color.BLACK);
        texto.setTextSize(30);
        Paint quadrado = new Paint();
        quadrado.setStyle(Paint.Style.FILL);
        Paint borda = new Paint();
        borda.setStyle(Paint.Style.STROKE);
        borda.setColor(Color.BLACK);

        canvas.drawRect(inicio, 0, larguraPx, alturaPx, borda);

        for (int i=0; i<Grid.LARGURA; i++) {
            for (int j=0; j<Grid.ALTURA; j++) {
                quadrado.setColor(getColor(i, j));
                canvas.drawRect(inicio + i*tamanho, j*tamanho, inicio + tamanho + i*tamanho, tamanho + j*tamanho, quadrado);
                canvas.drawRect(inicio + i*tamanho, j*tamanho, inicio + tamanho + i*tamanho, tamanho + j*tamanho, borda);
                canvas.drawText(i + ", " + j, inicio + i*tamanho, 20 + j*tamanho, texto);
            }
        }
    }

    private int getColor(int i, int j) {
        switch (grid.get(i, j)) {
            case 1:
                return Color.CYAN;
            case 2:
                return Color.YELLOW;
            case 3:
                return Color.argb(255, 255, 192, 203);
            case 4:
                return Color.BLUE;
            case 5:
                return Color.argb(255, 255, 153, 0);
            case 6:
                return Color.GREEN;
            case 7:
                return Color.RED;
            default:
                return Color.WHITE;
        }
    }
}
