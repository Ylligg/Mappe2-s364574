package com.example.mappe2_s364574;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataKildeVenner {
    private SQLiteDatabase database;
    private DatabasehelperVenner dbHelper;

    public DataKildeVenner(Context context) {
        dbHelper = new DatabasehelperVenner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Venner leggInnVenn(String navn, String telefonnr) {
        ContentValues values = new ContentValues();
        values.put(DatabasehelperVenner.KOLONNE_VENN_NAVN, navn);
        values.put(DatabasehelperVenner.KOLONNE_VENN_TLF, telefonnr);
        long insertId = database.insert(DatabasehelperVenner.TABELL_VENNER, null,
                values);
        Cursor cursor = database.query(DatabasehelperVenner.TABELL_VENNER, null,
                DatabasehelperVenner.KOLONNE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Venner nyVenn = cursorTilVenn(cursor);
        cursor.close();
        return nyVenn;
    }

    private static Venner cursorTilVenn(Cursor cursor) {
        Venner venn = new Venner();
        venn.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabasehelperVenner
                .KOLONNE_ID)));
        venn.setNavn(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabasehelperVenner.KOLONNE_VENN_NAVN)));
        venn.setTelefonnummer(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabasehelperVenner.KOLONNE_VENN_TLF)));
        return venn;
    }

    public List<Venner> finnAlleVenner() {
        List<Venner> venner = new ArrayList<>();
        Cursor cursor = database.query(DatabasehelperVenner.TABELL_VENNER, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Venner venn = cursorTilVenn(cursor);
            venner.add(venn);
            cursor.moveToNext();
        }
        cursor.close();
        return venner;
    }

    public void slettVenn(long vennId) {
        database.delete(DatabasehelperVenner.TABELL_VENNER,
                DatabasehelperVenner.KOLONNE_ID + " =? ", new
                        String[]{Long.toString(vennId)});
    }
}

