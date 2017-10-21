package com.example.humbhenri.dedris;

import android.content.Context;
import android.media.AudioAttributes;
import android.media.SoundPool;
import android.os.Build;
import android.support.annotation.RequiresApi;

/**
 * Created by humbhenri on 21/10/17.
 */

public class Som {
    private static int TEMA;
    private SoundPool soundPool;
    private int streamTema;

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public Som(Context context) {
        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setContentType(AudioAttributes.CONTENT_TYPE_MUSIC).build();
        soundPool = new SoundPool.Builder().setAudioAttributes(audioAttributes).build();
        TEMA = soundPool.load(context, R.raw.tema_tetris, 0);
    }

    public void toca() {
        streamTema = soundPool.play(TEMA, 1, 1, 0, -1, 1.0f);
    }

    public void pausa() {
        soundPool.pause(streamTema);
    }

    public void para() {
        soundPool.stop(streamTema);
    }

}
