package com.book_app.book_reader_prod.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.SharedPreferences;

public class SettingsManager{
    private static final String PREF_NAME = "BookReaderSettings";
    private static final String KEY_SETTING_1 = "switchTheme";
    private static final String KEY_SETTING_2 = "switchPlayerActive";
    private final SharedPreferences sharedPreferences;

    @SuppressLint("CommitPrefEdits")
    public SettingsManager(Context context) {
        sharedPreferences = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
    }


    public void setThemeSwitched(boolean value) {
        sharedPreferences.edit().putBoolean(KEY_SETTING_1, value).apply();
    }

    public boolean getThemeSwitched() {
        return sharedPreferences.getBoolean(KEY_SETTING_1, false);
    }

    public void setPlayerActive(boolean value) {
        sharedPreferences.edit().putBoolean(KEY_SETTING_2, value).apply();
    }

    public boolean getPlayerActive() {
        return sharedPreferences.getBoolean(KEY_SETTING_2, false);
    }
}