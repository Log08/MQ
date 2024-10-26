package com.log.mq;

import android.content.Context;
import android.content.SharedPreferences;

import de.robv.android.xposed.XSharedPreferences;

public class Sp {
    private static final String Config = "Config";
    private static SharedPreferences preferences;
    private static SharedPreferences.Editor editor;
    private static XSharedPreferences xSharedPreferences;

    public static void SP_init(Context mcontext) {
        preferences = mcontext.getSharedPreferences(Config, Context.MODE_WORLD_READABLE);
        editor = preferences.edit();
    }

    public static void XSp_init() {
        xSharedPreferences = new XSharedPreferences("com.log.mq", Config);
    }
    public static void putBoolen(String key, Boolean value){
        editor.putBoolean(key, value);
        editor.apply();
    }
    public static Boolean Hook_getBoolean(String key){
        return xSharedPreferences.getBoolean(key, false);
    }
}
