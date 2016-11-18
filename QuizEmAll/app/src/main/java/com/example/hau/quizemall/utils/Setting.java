package com.example.hau.quizemall.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by Hau on 18/11/2016.
 */
public class Setting {
    private int height;
    private int width;

    private Setting(Context context) {
        DisplayMetrics metrics = context.getResources().getDisplayMetrics();
        height = metrics.heightPixels;
        width = metrics.widthPixels;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public static Setting instance;

    public static Setting getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Setting(context);
    }
}
