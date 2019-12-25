package com.example.zh.clms.utils;

import android.content.Context;
import android.content.SharedPreferences;

public class sp {
    private static final String FILE_NAME = "save_file_name";
    public static SharedPreferences sharedPreferences;

    public static void saveData(Context context, String user, String password) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("user", user);
        editor.putString("password", password);
        editor.commit();
    }

    public static Object getData(Context context) {
        sharedPreferences = context.getSharedPreferences(FILE_NAME, Context.MODE_PRIVATE);
        return null;
    }
}



