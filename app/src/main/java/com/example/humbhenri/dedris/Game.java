package com.example.humbhenri.dedris;

import android.content.Context;
import android.graphics.Canvas;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by humbhenri on 12/10/17.
 */

class Game extends SurfaceView implements Runnable, GridListener {
    private static final String TAG = Game.class.getName();
    private final Grid grid;
    private final GridPainter gridPainter;
    private volatile boolean running;
    private long ultimaVezTetraminoCaiu;
    private volatile int intervaloEntreMoveBaixo = 1000;
    private int intervaloMinimo = 100;
    private int intervaloAnterior;

    public Game(Context context) {
        super(context);
        grid = new Grid(new CriadorTetraminoAleatorio(), this);
        gridPainter = new GridPainter(grid);

        setOnTouchListener(new MyGestureListener(context) {
            @Override
            public void onSwipeLeft() {
                if (running) grid.moveEsquerda();
            }

            @Override
            public void onSwipeRight() {
                if (running) grid.moveDireita();
            }

            @Override
            public void onClick() {
                if (running) grid.rotacionaTetramino();
            }

            @Override
            public void onSwipeDown() {
                if (running) {
                    grid.moveBaixo();
                    intervaloAnterior = intervaloEntreMoveBaixo;
                    intervaloEntreMoveBaixo = intervaloMinimo;
                    new Timer().schedule(new TimerTask() {
                        @Override
                        public void run() {
                            intervaloEntreMoveBaixo = intervaloAnterior;
                        }
                    }, 1000);
                }
            }
        });
    }

    @Override
    public void run() {
        while (running) {
            if (!getHolder().getSurface().isValid()) continue;
            Canvas canvas = getHolder().lockCanvas();
            gridPainter.draw(canvas);

            long currentTimeMillis = System.currentTimeMillis();
            if (currentTimeMillis - ultimaVezTetraminoCaiu > intervaloEntreMoveBaixo) {
                grid.moveBaixo();
                ultimaVezTetraminoCaiu = currentTimeMillis;
            }

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                Log.e(TAG, e.getMessage());
            }
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void pause() {
        running = false;
    }

    public void inicia() {
        running = true;
    }

    @Override
    public void tetraminoGrudou() {
        // se fez um swipe pra baixo depois do tetramino grudar é melhor voltar à velocidade anterior.
        intervaloEntreMoveBaixo = intervaloAnterior;
    }

    @Override
    public void jogoAcabou() {
        new Handler(Looper.getMainLooper()).post(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
                pause();
            }
        });
    }

    @Override
    public void removeuLinha(int linha) {
        // TODO
    }

    public boolean isRunning() {
        return running;
    }
}
