package com.senai.sharedpreferences.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private final String NAME_DATABASE = "db_senai.db";
    private final int VERSION_DATABASE = 1;

    public DatabaseHelper(Context context){
        super(context, "db_senai.db", null, 1);
    }


    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "CREATE TABLE student(" +
                "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name VARCHAR(45) NOT NULL," +
                "email VARCHAR(45) NOT NULL," +
                "password VARCHAR(45) NOT NULL);";

        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String sql = "DROP TABLE IF EXISTS student;";
        sqLiteDatabase.execSQL(sql);
        onCreate(sqLiteDatabase);
    }
}
