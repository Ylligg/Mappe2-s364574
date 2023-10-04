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
    private DatabasehjelperVenner dbHelper;

    public DataKildeVenner(Context context) {
        dbHelper = new DatabasehjelperVenner(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Venner leggInnVenn(String navn, String telefonnr) {
        ContentValues values = new ContentValues();
        values.put(DatabasehjelperVenner.KOLONNE_VENN_NAVN, navn);
        values.put(DatabasehjelperVenner.KOLONNE_VENN_TELEFONNR, telefonnr);
        long insertId = database.insert(DatabasehjelperVenner.TABELL_VENNER, null,
                values);
        Cursor cursor = database.query(DatabasehjelperVenner.TABELL_VENNER, null,
                DatabasehjelperVenner.KOLONNE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Venner nyVenn = cursorTilVenn(cursor);
        cursor.close();
        return nyVenn;
    }

    private static Venner cursorTilVenn(Cursor cursor) {
        Venner venn = new Venner();
        venn.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabasehjelperVenner
                .KOLONNE_ID)));
        venn.setNavn(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabasehjelperVenner.KOLONNE_VENN_NAVN)));
        venn.setTelefonnummer(cursor.getString(cursor.getColumnIndexOrThrow(
                DatabasehjelperVenner.KOLONNE_VENN_TELEFONNR)));
        return venn;
    }

    public List<Venner> finnAlleVenner() {
        List<Venner> venner = new ArrayList<>();
        Cursor cursor = database.query(DatabasehjelperVenner.TABELL_VENNER, null,
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

    public void updateVenn(long id, String nyNavn, String nytlf) {
        ContentValues values = new ContentValues();
        values.put(DatabasehjelperVenner.KOLONNE_VENN_NAVN, nyNavn);
        values.put(DatabasehjelperVenner.KOLONNE_VENN_TELEFONNR, nytlf);
        database.update(DatabasehjelperVenner.TABELL_VENNER, values,
                DatabasehjelperVenner.KOLONNE_ID + " =? ", new
                        String[]{Long.toString(id)});

    }

    public void slettVenn(long id) {

            database.delete(DatabasehjelperVenner.TABELL_VENNER,
                    DatabasehjelperVenner.KOLONNE_ID + " =? ", new
                            String[]{Long.toString(id)});

    }
}

