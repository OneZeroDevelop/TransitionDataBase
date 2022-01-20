package com.test.myfinance;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBHelper extends SQLiteOpenHelper {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "transitionsDB";
    public static final String TABLE_TRANSITIONS = "transitions";

    public static final String KEY_ID = "_id";
    public static final String KEY_SUM = "sum";
    public static final String KEY_NAME = "name";



    public DBHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_TRANSITIONS + "(" + KEY_ID
                + " integer primary key," + KEY_NAME + " text," + KEY_SUM + " text" + ")");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists " + TABLE_TRANSITIONS);

        onCreate(db);
    }


    Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_TRANSITIONS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db != null) {
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

}
