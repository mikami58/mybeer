package com.mikami.bymybeer.Domain.Beer;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.mikami.bymybeer.Domain.Photo.PhotoQueries;

import java.util.ArrayList;
import java.util.Collection;

public class BeerQueries {

    public static void createTable(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + BeerTable.TABLE_BEER + "("
                + BeerTable.BEER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + BeerTable.BEER_NAME + " TEXT,"
                + BeerTable.BEER_TASTE + " INTEGER)");
    }

    public static void dropTable(SQLiteDatabase db) {
        db.execSQL("drop table if exists " + BeerTable.TABLE_BEER);
    }

    public static long write(SQLiteDatabase db, String name, int taste) {
        ContentValues values = new ContentValues();
        values.put(BeerTable.BEER_NAME, name);
        values.put(BeerTable.BEER_TASTE, taste);
        return db.insert(BeerTable.TABLE_BEER, null, values);
    }

    public static Collection<Beer> read(SQLiteDatabase db) {
        Collection<Beer> result = new ArrayList<>();
        Cursor cursor = db.query(BeerTable.TABLE_BEER, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            int idIndex = cursor.getColumnIndex(BeerTable.BEER_ID);
            int nameIndex = cursor.getColumnIndex(BeerTable.BEER_NAME);
            int tasteIndex = cursor.getColumnIndex(BeerTable.BEER_TASTE);
            do {
                result.add(new Beer(
                        cursor.getLong(idIndex),
                        cursor.getString(nameIndex),
                        PhotoQueries.read(db, cursor.getLong(idIndex)),
                        cursor.getInt(tasteIndex)
                ));
            } while (cursor.moveToNext());
        }
        cursor.close();
        return result;
    }
}
