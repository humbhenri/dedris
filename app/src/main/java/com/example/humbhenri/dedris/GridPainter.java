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
        for (int i=0; i<Grid.LARGURA; i++) {
            for (int j=0; j<Grid.ALTURA; j++) {
                switch (grid.get(i, j)) {
                    case 1:
                        quadrado.setColor(Color.CYAN);
                        break;
                    case 2:
                        quadrado.setColor(Color.YELLOW);
                        break;
                    case 3:
                        quadrado.setColor(Color.argb(255, 255, 192, 203));
                        break;
                    case 4:
                        quadrado.setColor(Color.BLUE);
                        break;
                    case 5:
                        quadrado.setColor(Color.argb(255, 255, 153, 0));
                        break;
                    case 6:
                        quadrado.setColor(Color.GREEN);
                        break;
                    case 7:
                        quadrado.setColor(Color.RED);
                        break;
                    default:
                        quadrado.setColor(Color.WHITE);
                }
                canvas.drawRect(inicio + i*tamanho, j*tamanho, inicio + tamanho + i*tamanho, tamanho + j*tamanho, quadrado);
                canvas.drawRect(inicio + i*tamanho, j*tamanho, inicio + tamanho + i*tamanho, tamanho + j*tamanho, borda);
                canvas.drawText(i + ", " + j, inicio + i*tamanho, 20 + j*tamanho, texto);
            }
        }
    }
}
