package entities;

public class Plan {
    private double imc;
    private double poids;
    private double taille;
    private String sexe;
    private String niveauActivite;
    private String regime;
    private String objectif;
    private int age;

    public Plan(double imc, double poids, double taille, String sexe, String niveauActivite, String regime, String objectif, int age) {
        this.imc = imc;
        this.poids = poids;
        this.taille = taille;
        this.sexe = sexe;
        this.niveauActivite = niveauActivite;
        this.regime = regime;
        this.objectif = objectif;
        this.age = age;
    }

    public Plan(int imcId, String niveauActivite, String regime, String objectif, int age, double besoinsCaloriques) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plan() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Plan(double poids, double taille, String niveauActivite, String regime, String objectif, int age) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public double getImc() {
        return imc;
    }

    public void setImc(double imc) {
        this.imc = imc;
    }

    public double getPoids() {
        return poids;
    }

    public void setPoids(double poids) {
        this.poids = poids;
    }

    public double getTaille() {
        return taille;
    }

    public void setTaille(double taille) {
        this.taille = taille;
    }

    public String getSexe() {
        return sexe;
    }

    public void setSexe(String sexe) {
        this.sexe = sexe;
    }

    public String getNiveauActivite() {
        return niveauActivite;
    }

    public void setNiveauActivite(String niveauActivite) {
        this.niveauActivite = niveauActivite;
    }

    public String getRegime() {
        return regime;
    }

    public void setRegime(String regime) {
        this.regime = regime;
    }

    public String getObjectif() {
        return objectif;
    }

    public void setObjectif(String objectif) {
        this.objectif = objectif;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public double calculerBesoinsCaloriques() {
        double mb;
        if (sexe.equals("homme")) {
            mb = (10 * poids) + (6.25 * taille) - (5 * age) + 5;
        } else {
            mb = (10 * poids) + (6.25 * taille) - (5 * age) - 161;
        }

        // Définition des paliers d'activité
        double palierActivite = 1.0;
        if (niveauActivite.equals("légèrement actif")) {
            palierActivite = 1.375;
        } else if (niveauActivite.equals("modérément actif")) {
            palierActivite = 1.55;
        } else if (niveauActivite.equals("très actif")) {
            palierActivite = 1.725;
        } else if (niveauActivite.equals("extrêmement actif")) {
            palierActivite = 1.9;
        }

        // Facteur pour prendre en compte l'objectif
        double facteurObjectif = 1.0; // Pas de modification
        if (objectif.equals("perte de poids")) {
            facteurObjectif = 0.8; // Réduction de 20% des besoins caloriques
        } else if (objectif.equals("tonifier les muscles")) {
            facteurObjectif = 1.2; // Augmentation de 20% des besoins caloriques
        }

        // Facteur pour prendre en compte le régime alimentaire
        double facteurRegime = 1.0; // Pas de modification
        if (regime.contains("intolérance au lactose")) {
            facteurRegime *= 0.9; // Réduction de 10% pour l'intolérance au lactose
        }
        if (regime.contains("ne mange pas de gluten")) {
            facteurRegime *= 0.9; // Réduction de 10% pour l'absence de gluten
        }
        if (regime.contains("je suis végétarienne")) {
            facteurRegime *= 0.9; // Réduction de 10% pour les végétariens
        }

        // Calcul des besoins caloriques totaux
        double besoinsCaloriques = mb * palierActivite * facteurObjectif * facteurRegime;

        return besoinsCaloriques;
    }

    // Méthode pour afficher les besoins caloriques
    public void afficherBesoinsCaloriques() {
        double besoinsCaloriques = calculerBesoinsCaloriques();
        System.out.println("Besoins caloriques : " + besoinsCaloriques + " calories par jour.");
    }
}

