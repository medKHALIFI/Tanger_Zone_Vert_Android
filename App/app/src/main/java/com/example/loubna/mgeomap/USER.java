package com.example.loubna.mgeomap;

public class USER {
    public class Metier {
        // Notez que l'identifiant est un long
        private long id;
        private String intitule;
        private float salaire;

        public Metier(long id, String intitule, float salaire) {
            super();
            this.id = id;
            this.intitule = intitule;
            this.salaire = salaire;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        public String getIntitule() {
            return intitule;
        }

        public void setIntitule(String intitule) {
            this.intitule = intitule;
        }

        public float getSalaire() {
            return salaire;
        }

        public void setSalaire(float salaire) {
            this.salaire = salaire;
        }

    }


}
