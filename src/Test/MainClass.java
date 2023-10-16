package Test;

import entities.Imc;
import services.ImcCrud;

public class MainClass {
    public static void main(String[] args) {
        // Créer une instance d'IMC avec des valeurs spécifiques
        Imc imc = new Imc();
        imc.setSexe("Homme"); // Définir le sexe
        imc.setTaille(160);   // Définir la taille en cm
        imc.setPoids(60);    // Définir le poids en kg

        // Calculer l'IMC
        imc.calculerIMC();

        // Calculer et définir le poids idéal
        imc.calculerPoidsIdeal();

        // Insérer l'IMC dans la base de données
        ImcCrud imcCrud = new ImcCrud();
        boolean insertionReussie = imcCrud.insertImc(imc);

        if (insertionReussie) {
            System.out.println("IMC inséré avec succès dans la base de données.");
        } else {
            System.out.println("Erreur lors de l'insertion de l'IMC dans la base de données.");
        }
    }
}
