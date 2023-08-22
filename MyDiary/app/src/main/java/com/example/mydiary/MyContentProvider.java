package com.example.mydiary;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;


public class MyContentProvider extends ContentProvider {
    static final String PROVIDER_NAME = "com.example.mydiary.MyContentProvider";
    static final String URL = "content://"+PROVIDER_NAME+"/mydiary";
    static final Uri CONTENT_URI = Uri.parse(URL);
    static final String NAME = "name";
    static final String EXPLANATION = "explanation";
    static final String IMAGE = "image";
    static final String LATITUDE = "latitude";
    static final String LONGITUDE = "longitude";

    public DiaryDBHelper dbHelper;
    public MyContentProvider(){  }
    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs){
        return dbHelper.delete(selection,selectionArgs);
    }
    @Override
    public String getType(Uri uri){
        return null;
    }
    @Override
    public Uri insert(Uri uri, ContentValues values){
        long rowid = dbHelper.insert(values);
        return null;
    }
    @Override
    public boolean onCreate(){
        dbHelper = DiaryDBHelper.getInstance(getContext());
        return true;
    }
    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder){
        return  dbHelper.query(projection, selection, selectionArgs,null,null,sortOrder);
    }
    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs){
        throw new UnsupportedOperationException("Not yet implemented");
    }
}
