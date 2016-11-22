package com.example.hau.quizemall.managers;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Hau on 21/11/2016.
 */
public class Preferences {
    private static final String KEY = "Pokemon";
    private static final String HIGH_SCORE = "HighScore";
    private static final String CURRENT_SCORE = "CurrentScore";
    private static final String IS_HIGH_SCORE = "IsHighScore";
    SharedPreferences sharedPreferences;

    public Preferences(Context context) {
        sharedPreferences = context.getSharedPreferences(KEY, Context.MODE_PRIVATE);
    }

    public int getHighScore() {
        return sharedPreferences.getInt(HIGH_SCORE, 0);
    }

    public void putHighScore(int highScore) {
        sharedPreferences.edit().putInt(HIGH_SCORE, highScore).apply();
    }

    public int getCurrentScore() {
        return sharedPreferences.getInt(CURRENT_SCORE, 0);
    }

    public boolean isHighScore() {
        return sharedPreferences.getBoolean(IS_HIGH_SCORE, false);
    }

    public void putIsHighScre(boolean isHighScore) {
        sharedPreferences.edit().putBoolean(IS_HIGH_SCORE, isHighScore).apply();
    }

    public void putCurrentScore(int currentScore) {
        sharedPreferences.edit().putInt(CURRENT_SCORE, currentScore).apply();
    }
    private static Preferences instance;

    public static Preferences getInstance() {
        return instance;
    }

    public static void init(Context context) {
        instance = new Preferences(context);
    }
}
