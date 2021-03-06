package com.example.humbhenri.dedris;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.example.humbhenri.dedris.eventos.EventoGameOver;
import com.example.humbhenri.dedris.eventos.EventoLinhaRemovida;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Game game;
    private Button startStop;
    private Thread gameThread;
    private Som som;
    private TextView scoreTV;
    private int score;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout container = findViewById(R.id.container);
        game = new Game(this);
        container.addView(game);
        startStop = findViewById(R.id.begin);
        startStop.setOnClickListener(this);
        som = new Som(getApplicationContext());
        scoreTV = findViewById(R.id.score);
        score = 0;
        escreveScore();
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameThread = new Thread(game);
        gameThread.start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();
        try {
            gameThread.join();
        } catch (InterruptedException e) {
            Log.e(TAG, e.getMessage());
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.begin:
                if (game.isRunning()) {
                    pausa();
                } else {
                    new Thread(game).start();
                    if (game.isGameOver()) {
                        reinicia();
                    } else {
                        inicia();
                    }
                    startStop.setText(R.string.stop);
                }
                break;
        }
    }

    private void pausa() {
        game.pause();
        startStop.setText(R.string.start);
        som.pausa();
    }

    private void reinicia() {
        game.reinicia();
        score = 0;
        escreveScore();
        som.toca();
    }

    private void inicia() {
        game.inicia();
        som.toca();
    }

    private void escreveScore() {
        scoreTV.setText(String.valueOf(score));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void jogoTerminou(EventoGameOver evento) {
        startStop.setText(R.string.start);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void linhaRemovida(EventoLinhaRemovida e) {
        atualizaScore(e.quantidade);
    }

    private void atualizaScore(int quantidade) {
        score += 100 * quantidade;
        escreveScore();
    }
}
