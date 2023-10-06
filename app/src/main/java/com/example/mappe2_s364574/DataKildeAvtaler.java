package com.example.mappe2_s364574;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

public class DataKildeAvtaler {
    private SQLiteDatabase database;
    private DatabasehjelperAvtale dbHelper;

    public DataKildeAvtaler(Context context) {
        dbHelper = new DatabasehjelperAvtale(context);
    }

    public void open() throws SQLException {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public Avtale leggInnAvtale(String navn, String dato, String klokke, String sted) {

        ContentValues values = new ContentValues();
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_NAVN, navn);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_DATO, dato);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_KLOKKE, klokke);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_STED, sted);

        long insertId = database.insert(DatabasehjelperAvtale.TABELL_AVTALE, null, values);
        Cursor cursor = database.query(DatabasehjelperAvtale.TABELL_AVTALE, null,
                DatabasehjelperAvtale.KOLONNE_ID + " = " + insertId, null, null, null, null);
        cursor.moveToFirst();
        Avtale nyavtale = cursorTilAvtale(cursor);
        cursor.close();
        return nyavtale;
    }

    private static Avtale cursorTilAvtale(Cursor cursor) {
        Avtale avtale = new Avtale();
        avtale.setId(cursor.getLong(cursor.getColumnIndexOrThrow(DatabasehjelperAvtale.KOLONNE_ID)));
        avtale.setNavnpåPerson(cursor.getString(cursor.getColumnIndexOrThrow(DatabasehjelperAvtale.KOLONNE_AVTALE_NAVN)));
        avtale.setDatoforMøte(cursor.getString(cursor.getColumnIndexOrThrow(DatabasehjelperAvtale.KOLONNE_AVTALE_DATO)));
        avtale.setKlokkeslettforMøte(cursor.getString(cursor.getColumnIndexOrThrow(DatabasehjelperAvtale.KOLONNE_AVTALE_KLOKKE)));
        avtale.setMøtested(cursor.getString(cursor.getColumnIndexOrThrow(DatabasehjelperAvtale.KOLONNE_AVTALE_STED)));
        return avtale;
    }

    public List<Avtale> finnAlleAvtaler() {
        List<Avtale> avtaler = new ArrayList<>();
        Cursor cursor = database.query(DatabasehjelperAvtale.TABELL_AVTALE, null,
                null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Avtale avtale = cursorTilAvtale(cursor);
            avtaler.add(avtale);

            cursor.moveToNext();
        }
        cursor.close();
        return avtaler;
    }

    public void updateAvtale(long id, String nyNavn, String nyDato, String nyKlokke, String nySted) {

        ContentValues values = new ContentValues();
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_NAVN, nyNavn);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_DATO, nyDato);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_KLOKKE, nyKlokke);
        values.put(DatabasehjelperAvtale.KOLONNE_AVTALE_STED, nySted);

        database.update(DatabasehjelperAvtale.TABELL_AVTALE, values,
                DatabasehjelperAvtale.KOLONNE_ID + " =? ", new
                        String[]{Long.toString(id)});

    }

    public void slettVenn(long id) {

        database.delete(DatabasehjelperAvtale.TABELL_AVTALE,
                DatabasehjelperAvtale.KOLONNE_ID + " =? ", new
                        String[]{Long.toString(id)});

    }
}