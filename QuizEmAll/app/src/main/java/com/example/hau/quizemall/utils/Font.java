package com.example.hau.quizemall.utils;

import android.content.Context;
import android.graphics.Typeface;

import com.example.hau.quizemall.MainActivity;

/**
 * Created by Hau on 18/11/2016.
 */
public class Font {
    private static final String FONT_STENCIL = "fonts/StencilStd.ttf";
    private static final String FONT_POPLAR = "fonts/PoplarStd.ttf";
    private Typeface fontStencil;
    private Typeface fontPoplar;

    private Font(Context context) {
        fontStencil = Typeface.createFromAsset(context.getAssets(), FONT_STENCIL);
        fontPoplar = Typeface.createFromAsset(context.getAssets(), FONT_POPLAR);
    }

    public Typeface getFontPoplar() {
        return fontPoplar;
    }

    public Typeface getFontStencil() {
        return fontStencil;
    }

    public static Font instance;

    public static Font getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Font(context);
    }
}
