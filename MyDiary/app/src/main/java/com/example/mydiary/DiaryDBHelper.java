package com.example.mydiary;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DiaryDBHelper extends SQLiteOpenHelper {
    static final String MY_DIARY_DB ="Diary.db";
    static final String MY_DIARY_TABLE = "MyDiaries";
    static int VERSION = 1;

    Context context = null;
    private static DiaryDBHelper dbHelper = null;

    static final String CREATE_DB = " CREATE TABLE "+MY_DIARY_TABLE+" (_id INTEGER PRIMARY KEY AUTOINCREMENT, "+"name TEXT NOT NULL, explanation TEXT NOT NULL, image TEXT NOT NULL, latitude TEXT NOT NULL, longitude TEXT);";

    public static DiaryDBHelper getInstance(Context context){
        if(dbHelper == null){
            dbHelper = new DiaryDBHelper(context, MY_DIARY_DB, null, VERSION);
        }
        return dbHelper;
    }

    public DiaryDBHelper(Context context, String dbName, SQLiteDatabase.CursorFactory factory, int version){
        super(context, dbName, factory, version);
        this.context = context;
    }

    @Override
    public void onOpen(SQLiteDatabase db){
        super.onOpen(db);
    }
    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL(CREATE_DB);
    }
    public void onUpgrade(SQLiteDatabase db, int oldV, int newV){

    }
    public long insert(ContentValues addValue){
        return getWritableDatabase().insert(MY_DIARY_TABLE,null,addValue);
    }
    public Cursor query(String[] columns, String selection, String[] selectionArgs, String groupBy, String having, String orderBy){
        return getReadableDatabase().query(MY_DIARY_TABLE, columns, selection, selectionArgs, groupBy, having, orderBy);
    }
    public int delete(String whereClause, String[] whereArgs){
        return getWritableDatabase().delete(MY_DIARY_TABLE,whereClause,whereArgs);
    }

}
