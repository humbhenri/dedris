package com.example.humbhenri.dedris;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

public class MainActivity extends Activity implements View.OnClickListener {

    private Game game;
    private Button startStop;


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
        new Thread(game).start();
    }

    @Override
    protected void onPause() {
        super.onPause();
        game.pause();

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.begin:
                if (game.isRunning()) {
                    game.pause();
                    startStop.setText(R.string.start);
                } else {
                    game.inicia();
                    startStop.setText(R.string.stop);
                }
                break;
        }
    }

}
