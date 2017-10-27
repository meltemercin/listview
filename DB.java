package com.example.java_oglen.myapplication;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by java_oglen on 27.10.2017.
 */

public class DB extends SQLiteOpenHelper {
    private static String dbName="proje.db";
    private static int dbVersion=1;

    public DB(Context context) {
        super(context,dbName,null,dbVersion);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String sql="CREATE TABLE `kisiler` (\n" +
                "\t`kid`\tINTEGER PRIMARY KEY AUTOINCREMENT,\n" +
                "\t`adi`\tTEXT,\n" +
                "\t`soyadi`\tTEXT,\n" +
                "\t`mail`\tTEXT,\n" +
                "\t`telefon`\tTEXT\n" +
                ");";
       db.execSQL(sql);



    }


    public void onUpgrade(SQLiteDatabase db,int oldVersion,int newVersion)
    {
        db.execSQL("drop table if exists kisiler");
        onCreate(db);
    }
}
