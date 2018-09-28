package com.example.loubna.mgeomap;

import android.content.Context;

public class USERDAO  extends DAOBASE {

        public static final String TABLE_NAME = "metier";
        public static final String KEY = "id";
        public static final String INTITULE = "intitule";
        public static final String SALAIRE = "salaire";

        public static final String TABLE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" + KEY + " INTEGER PRIMARY KEY AUTOINCREMENT, " + INTITULE + " TEXT, " + SALAIRE + " REAL);";

        public static final String TABLE_DROP =  "DROP TABLE IF EXISTS " + TABLE_NAME + ";";

    public USERDAO(Context pContext) {
        super(pContext);
    }

    /**
         * @param m le métier à ajouter à la base
         */
        public void ajouter(USER m) {
            // CODE
          // INSERT INTO Metier (Salaire, Metier) VALUES (50.2, "Caricaturiste")
        }

        /**
         * @param id l'identifiant du métier à supprimer
         */
        public void supprimer(long id) {
            // CODE
        }

        /**
         * @param m le métier modifié
         */
        public void modifier(USER m) {
            // CODE
        }

        /**
         * @param id l'identifiant du métier à récupérer
         */
        public USER selectionner(long id) {
            // CODE
            return null ;
        }
    }

