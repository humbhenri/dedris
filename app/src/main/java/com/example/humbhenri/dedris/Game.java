package com.example.humbhenri.dedris;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Log;
import android.view.SurfaceView;
import android.widget.Toast;

import com.example.humbhenri.dedris.eventos.EventoGameOver;
import com.example.humbhenri.dedris.eventos.EventoLinhaRemovida;
import com.example.humbhenri.dedris.eventos.EventoTetraminoGrudou;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by humbhenri on 12/10/17.
 */

class Game extends SurfaceView implements Runnable {
    private static final String TAG = Game.class.getName();
    private Grid grid;
    private GridPainter gridPainter;
    private volatile boolean running;
    private long ultimaVezTetraminoCaiu;
    private int intervaloEntreMoveBaixo = 1000;
    private int intervaloAnterior = 1000;
    private int intervaloMinimo = 100;

    public Game(Context context) {
        super(context);
        iniciaGrid();
        EventBus.getDefault().register(this);

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

    private void iniciaGrid() {
        grid = new Grid(new CriadorTetraminoAleatorio());
        gridPainter = new GridPainter(grid);
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

    @Subscribe
    public void tetraminoGrudou(EventoTetraminoGrudou evento) {
        // se fez um swipe pra baixo depois do tetramino grudar é melhor voltar à velocidade anterior.
        intervaloEntreMoveBaixo = intervaloAnterior;
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void jogoAcabou(EventoGameOver evento) {
        Toast.makeText(getContext(), "Game Over", Toast.LENGTH_SHORT).show();
        pause();
    }

    @Subscribe
    public void removeuLinha(EventoLinhaRemovida evento) {
        // TODO
    }

    public boolean isRunning() {
        return running;
    }

    public void reinicia() {
        iniciaGrid();
    }
}
