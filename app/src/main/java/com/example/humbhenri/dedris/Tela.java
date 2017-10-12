package com.example.humbhenri.dedris;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;

/**
 * Created by humbhenri on 12/10/17.
 */

public class Tela {

    private Context context;
    private final DisplayMetrics metrics;

    public Tela(Context context){
        this.context = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        metrics = new DisplayMetrics();
        display.getMetrics(metrics);
    }

    public int altura() {
        return metrics.heightPixels;
    }

    public int largura() {
        return metrics.widthPixels;
    }
}
