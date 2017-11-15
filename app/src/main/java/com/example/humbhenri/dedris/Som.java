package com.example.humbhenri.dedris;

import android.content.Context;
import android.media.MediaPlayer;

/**
 * Created by humbhenri on 21/10/17.
 */

public class Som {
    MediaPlayer mediaPlayer;
    public Som(Context context) {
        mediaPlayer = MediaPlayer.create(context, R.raw.gameboy);
        mediaPlayer.setLooping(true);
    }

    public void toca() {
        mediaPlayer.start();
    }

    public void pausa() {
        mediaPlayer.pause();
    }

    public void para() {
        mediaPlayer.stop();
    }

}
