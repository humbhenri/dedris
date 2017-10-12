package com.example.humbhenri.dedris;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.SurfaceView;
import android.view.View;
import android.view.WindowManager;

/**
 * Created by humbhenri on 12/10/17.
 */

class Game extends SurfaceView implements Runnable{


    public static final int ALTURA = 20;
    public static final int LARGURA = 10;
    private final Grid grid;
    private final GridPainter gridPainter;
    private boolean isRunning;

    public Game(Context context) {
        super(context);
        grid = new Grid();
        gridPainter = new GridPainter(grid, new Tela(context));
    }

    @Override
    public void run() {
        while(isRunning) {
            if (!getHolder().getSurface().isValid()) continue;

            Canvas canvas = getHolder().lockCanvas();

            // desenha tudo
            gridPainter.draw(canvas);

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        isRunning = false;
    }

    public void inicia() {
        isRunning = true;
    }
}
