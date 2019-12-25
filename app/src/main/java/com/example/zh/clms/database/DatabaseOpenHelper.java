package com.example.zh.clms.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseOpenHelper extends SQLiteOpenHelper {

    //数据库名
    private static String DatabaseName = "mydb.db";
    //版本号
    private static int version = 1;

    public DatabaseOpenHelper(Context context) {
        super(context, DatabaseName, null, version);

    }

    // 当数据库创建的时候，是第一次被执行，完成对数据库的表的创建
    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table stu(id integer primary key autoincrement,userName varchar" +
                "" + "(64),password varchar(64))";
        db.execSQL(sql);

        String sql_tea = "create table tea(id integer primary key autoincrement,userName varchar"
                + "" + "(64),password varchar(64))";
        db.execSQL(sql_tea);

        String sql_admin = "create table admin(id integer primary key autoincrement,userName " +
                "varchar" + "" + "(64),password varchar(64))";
        db.execSQL(sql_admin);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
