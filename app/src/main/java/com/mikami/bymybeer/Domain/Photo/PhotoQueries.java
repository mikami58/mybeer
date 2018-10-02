package com.mikami.bymybeer.Domain.Photo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.mikami.bymybeer.Domain.Beer.Beer;
import com.mikami.bymybeer.Domain.Beer.BeerTable;

import java.util.ArrayList;
import java.util.Collection;

public class PhotoQueries {

    public static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + PhotoTable.TABLE_PHOTO + "("
                + PhotoTable.PHOTO_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + PhotoTable.PHOTO_BEER_ID + " INTEGER,"
                + PhotoTable.PHOTO_PATH + " TEXT,"
                + "FOREIGN KEY(" + PhotoTable.PHOTO_BEER_ID + ") REFERENCES "
                + BeerTable.TABLE_BEER + "(" + BeerTable.BEER_ID + "))");
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + PhotoTable.TABLE_PHOTO);
    }

    public static long write(SQLiteDatabase db, String path, int beer_id) {
        ContentValues values = new ContentValues();
        values.put(PhotoTable.PHOTO_PATH, path);
        values.put(PhotoTable.PHOTO_BEER_ID, beer_id);
        return db.insert(PhotoTable.TABLE_PHOTO, null, values);
    }

    public static Photo read(SQLiteDatabase db, long id) {
        Photo photo = new Photo(-1, -1, "");
        String query = "SELECT * FROM " + PhotoTable.TABLE_PHOTO + " WHERE " + PhotoTable.PHOTO_BEER_ID + " = ?";
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(id)});
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(PhotoTable.PHOTO_ID);
            int beerIdIndex = cursor.getColumnIndex(PhotoTable.PHOTO_BEER_ID);
            int pathIndex = cursor.getColumnIndex(PhotoTable.PHOTO_PATH);
            photo = new Photo(cursor.getLong(idIndex), cursor.getLong(beerIdIndex), cursor.getString(pathIndex));
        }
        cursor.close();
        return photo;
    }
}
