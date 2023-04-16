package com.example.kaunas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public  DBHelper (Context context) {
        super(context, "feedback.db", null, 1);
    }
    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("create Table atsiliepimas(name TEXT primary key, feedbackText TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int i, int i1) {
        DB.execSQL("drop Table if exists atsiliepimas");
    }

    public boolean insertFeedback (String name, String feedbackText)
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", name);
        contentValues.put("feedbackText", feedbackText);
        long result = DB.insert("atsiliepimas", null, contentValues);
        if (result==-1)
        {
            return false;
        }
        else {
            return true;
        }
    }

    public Cursor getData()
    {
        SQLiteDatabase DB = this.getWritableDatabase();
        Cursor cursor = DB.rawQuery("Select * from atsiliepimas", null);
        return cursor;
    }
}
