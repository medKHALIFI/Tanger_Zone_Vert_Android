package com.example.loubna.mgeomap;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class DAOBASE {

        // Nous sommes à la première version de la base
        // Si je décide de la mettre à jour, il faudra changer cet attribut
        protected final static int VERSION = 1;
        // Le nom du fichier qui représente ma base
        protected final static String NOM = "database.db";

        protected SQLiteDatabase mDb = null;
        protected DataBase mHandler = null;

        public DAOBASE(Context pContext) {
            this.mHandler = new DataBase(pContext, NOM, null, VERSION);
        }

        public SQLiteDatabase open() {
            // Pas besoin de fermer la dernière base puisque getWritableDatabase s'en charge
            mDb = mHandler.getWritableDatabase();
            return mDb;
        }

        public void close() {
            mDb.close();
        }

        public SQLiteDatabase getDb() {
            return mDb;
        }
    }


