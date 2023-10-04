package com.example.mappe2_s364574;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabasehjelperVenner extends SQLiteOpenHelper {
    private static final String DATABASE_NAVN = "venner.db";
    private static final int DATABASE_VERSION = 1;
    public static final String TABELL_VENNER = "venner";
    public static final String KOLONNE_ID = "id";
    public static final String KOLONNE_VENN_NAVN = "navn";
    public static final String KOLONNE_VENN_TELEFONNR = "telefonnr";
    private static final String CREATE_TABLE_FRIENDS = "CREATE TABLE " +
            TABELL_VENNER +
            "(" + KOLONNE_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            KOLONNE_VENN_NAVN + " TEXT NOT NULL, " +
            KOLONNE_VENN_TELEFONNR + " TEXT NOT NULL)";
    public DatabasehjelperVenner(Context context) {
        super(context, DATABASE_NAVN, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE_FRIENDS);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    { onCreate(db);
    }
}
