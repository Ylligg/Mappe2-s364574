package com.example.mappe2_s364574;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasehjelperAvtale extends SQLiteOpenHelper {
    private static final String DATABASE_NAVN = "avtale.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABELL_AVTALE = "avtale";
    public static final String KOLONNE_ID = "id";
    public static final String KOLONNE_AVTALE_NAVN = "navnpåPerson";
    public static final String KOLONNE_AVTALE_DATO = "datoforMøte";
    public static final String KOLONNE_AVTALE_KLOKKE = "klokkeslettforMøte";
    public static final String KOLONNE_AVTALE_STED = "møtested";
    private static final String CREATE_TABLE_AVTALE = "CREATE TABLE " +
            TABELL_AVTALE +
            "(" + KOLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KOLONNE_AVTALE_NAVN + " TEXT NOT NULL, " +
            KOLONNE_AVTALE_DATO + " TEXT NOT NULL, " +
            KOLONNE_AVTALE_KLOKKE + " TEXT NOT NULL, " +
            KOLONNE_AVTALE_STED + " TEXT NOT NULL, " +
            ")";
    public DatabasehjelperAvtale(Context context) {
        super(context, DATABASE_NAVN, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_AVTALE);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { onCreate(db);
    }
}
