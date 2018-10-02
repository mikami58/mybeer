package com.mikami.bymybeer.Database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mikami.bymybeer.Domain.Beer.BeerQueries;
import com.mikami.bymybeer.Domain.Photo.PhotoQueries;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context) {
        super(context, DBParams.DATABASE_NAME, null, DBParams.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        createBase(db);
        writeTestData(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        destroyBase(db);
        onCreate(db);
    }

    private void createBase(SQLiteDatabase db) {
        BeerQueries.createTable(db);
        PhotoQueries.createTable(db);
    }

    private void destroyBase(SQLiteDatabase db) {
        BeerQueries.dropTable(db);
        PhotoQueries.dropTable(db);
    }

    private void writeTestData(SQLiteDatabase db) {
        BeerQueries.write(db, "Очаково", 3);
        BeerQueries.write(db, "Жатецкий гусь", 5);
        BeerQueries.write(db, "Бад", 7);
        BeerQueries.write(db, "Гиннес", 10);
        BeerQueries.write(db, "Очаково", 3);
        BeerQueries.write(db, "Жатецкий гусь", 5);
        BeerQueries.write(db, "Бад", 7);
        BeerQueries.write(db, "Гиннес", 10);
        BeerQueries.write(db, "Очаково", 3);
        BeerQueries.write(db, "Жатецкий гусь", 5);
        BeerQueries.write(db, "Бад", 7);
        BeerQueries.write(db, "Гиннес", 10);
        BeerQueries.write(db, "Очаково", 3);
        BeerQueries.write(db, "Жатецкий гусь", 5);
        BeerQueries.write(db, "Бад", 7);
        BeerQueries.write(db, "Гиннес", 10);
        BeerQueries.write(db, "Очаково", 3);
        BeerQueries.write(db, "Жатецкий гусь", 5);
        BeerQueries.write(db, "Бад", 7);
        BeerQueries.write(db, "Гиннес", 10);

        PhotoQueries.write(db, "1.jpg", 1);
        PhotoQueries.write(db, "2.jpg", 2);
        PhotoQueries.write(db, "3.jpg", 3);
        PhotoQueries.write(db, "4.jpg", 4);
        PhotoQueries.write(db, "5.jpg", 5);
        PhotoQueries.write(db, "6.jpg", 6);
        PhotoQueries.write(db, "7.jpg", 7);
        PhotoQueries.write(db, "8.jpg", 8);
        PhotoQueries.write(db, "9.jpg", 9);
        PhotoQueries.write(db, "10.jpg", 10);
    }


}
