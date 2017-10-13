package com.example.humbhenri.dedris;

import android.content.Context;
import android.graphics.Canvas;
import android.view.SurfaceView;

/**
 * Created by humbhenri on 12/10/17.
 */

class Game extends SurfaceView implements Runnable {


    private final Grid grid;
    private final GridPainter gridPainter;
    private boolean isRunning;
    private long ultimaVezTetraminoCaiu;

    public Game(Context context) {
        super(context);
        grid = new Grid();
        grid.coloca(Tetramino.RETA);
        gridPainter = new GridPainter(grid, new Tela(context));
        setOnTouchListener(new MyGestureListener(context) {
            @Override
            public void onSwipeLeft() {
                grid.moveEsquerda();
            }

            @Override
            public void onSwipeRight() {
                grid.moveDireita();
            }

            @Override
            public void onClick() {
                grid.rotacionaTetramino();
            }

            @Override
            public void onSwipeDown() {
                grid.moveBaixo();
            }
        });
    }

    @Override
    public void run() {
        while (isRunning) {
            if (!getHolder().getSurface().isValid()) continue;

            Canvas canvas = getHolder().lockCanvas();

            gridPainter.draw(canvas);
            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - ultimaVezTetraminoCaiu > 1000) {
                grid.moveBaixo();
                ultimaVezTetraminoCaiu = currentTimeMillis;
            }

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
