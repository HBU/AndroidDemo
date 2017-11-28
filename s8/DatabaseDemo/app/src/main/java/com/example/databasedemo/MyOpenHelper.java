package com.example.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

//创建一个抽象类SQLiteOpenHelper的实现类MyOpenHelper
public class MyOpenHelper extends SQLiteOpenHelper {
    /**
     * MyOpenHelper构造方法
     * @param context 上下文
     * @param name 数据库文件的名字
     * @param factory 游标工厂(结果集)
     * @param version 数据库的版本号(用于升级)
     */
    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {//创建数据库时,调用此方法
        Log.d("MainActivity", "数据库创建成功");
        //创建一个学生表
        db.execSQL("create table student(_id integer primary key autoincrement, name char(10), age integer, no integer, cpp float, math float, english float)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {//数据库升级时调用此方法
        Log.d("MainActivity", "数据库升级成功");
    }
}