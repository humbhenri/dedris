package com.example.humbhenri.dedris;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    public static final String TAG = MainActivity.class.getSimpleName();
    private Game game;
    private Button startStop;
    private Thread gameThread;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FrameLayout container = findViewById(R.id.container);
        game = new Game(this);
        container.addView(game);
        startStop = findViewById(R.id.begin);
        startStop.setOnClickListener(this);
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
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.begin:
                if (game.isRunning()) {
                    game.pause();
                    startStop.setText(R.string.start);
                } else {
                    new Thread(game).start();
                    game.inicia();
                    startStop.setText(R.string.stop);
                }
                break;
        }
    }

}
