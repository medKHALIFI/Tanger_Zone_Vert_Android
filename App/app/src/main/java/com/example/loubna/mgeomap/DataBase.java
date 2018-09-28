package com.example.loubna.mgeomap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DataBase  extends SQLiteOpenHelper{

        public static final String METIER_KEY = "id";
        public static final String METIER_INTITULE = "intitule";
        public static final String METIER_SALAIRE = "salaire";

        public static final String METIER_TABLE_NAME = "Metier";
        public static final String METIER_TABLE_CREATE =
                "CREATE TABLE " + METIER_TABLE_NAME + " (" +
                        METIER_KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        METIER_INTITULE + " TEXT, " +
                        METIER_SALAIRE + " REAL);";

        public DataBase(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(METIER_TABLE_CREATE);
        }
        public static final String METIER_TABLE_DROP = "DROP TABLE IF EXISTS " + METIER_TABLE_NAME + ";";

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }
    }




