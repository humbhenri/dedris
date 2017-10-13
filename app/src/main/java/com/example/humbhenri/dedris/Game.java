package com.example.humbhenri.dedris;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;

/**
 * Created by humbhenri on 12/10/17.
 */

class Game extends SurfaceView implements Runnable, View.OnTouchListener {


    public static final int ALTURA = 20;
    public static final int LARGURA = 10;
    private final Grid grid;
    private final GridPainter gridPainter;
    private boolean isRunning;

    public Game(Context context) {
        super(context);
        grid = new Grid();
        gridPainter = new GridPainter(grid, new Tela(context));
        setOnTouchListener(this);
    }

    @Override
    public void run() {
        while(isRunning) {
            if (!getHolder().getSurface().isValid()) continue;

            Canvas canvas = getHolder().lockCanvas();

            gridPainter.draw(canvas);
//            grid.moveBaixo();

            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        isRunning = false;
    }

    public void inicia() {
        isRunning = true;
    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        grid.moveBaixo();
        return false;
    }
}
