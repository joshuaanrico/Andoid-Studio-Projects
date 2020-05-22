package com.example.android.materialme;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;
import com.example.android.materialme.SavedBookContract.*;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "PerpusLockdown.db";
    public static final int DATABASE_VERSION = 2;
    public static final String TABLE_NAME = "savedBook";


    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_SAVEDBOOK_TABLE = "CREATE TABLE " +
                SavedBookEntry.TABLE_NAME + " (" +
                SavedBookEntry.COL_1 + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                SavedBookEntry.COL_2 + " TEXT NOT NULL, " +
                SavedBookEntry.COL_3 + " TEXT NOT NULL, " +
                SavedBookEntry.COL_4 + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP " +
                ");";
        db.execSQL(SQL_CREATE_SAVEDBOOK_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
