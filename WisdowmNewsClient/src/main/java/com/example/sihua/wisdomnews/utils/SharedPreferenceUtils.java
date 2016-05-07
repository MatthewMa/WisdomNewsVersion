package com.example.sihua.wisdomnews.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Sihua on 2016/5/6.
 */
public class SharedPreferenceUtils {
    public static final String CONFIG_NAME="config";
    public static void addSharedPreferenceBoolean(Context context,String name,Boolean flag){
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        sp.edit().putBoolean(name,flag).commit();
    }

    public static Boolean getSharedPreferenceBoolean(Context context,String name,Boolean
            defaultValue){
        SharedPreferences sp = context.getSharedPreferences(CONFIG_NAME, Context.MODE_PRIVATE);
        return sp.getBoolean(name,defaultValue);
    }
}
