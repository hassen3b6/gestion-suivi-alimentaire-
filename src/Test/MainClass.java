package Test;

import entities.Imc;
import entities.Plan;
import Services.ImcCrud;
import Services.PlanCrud;

public class MainClass {
    public static void main(String[] args) {
        // Créer une instance d'IMC avec des valeurs spécifiques
        Imc imc = new Imc("homme", 160, 60); // Utiliser le constructeur d'Imc

        // Calculer l'IMC
        imc.calculerIMC();

        // Calculer et définir le poids idéal
        imc.calculerPoidsIdeal();

     
        // Créer une instance de ImcCrud
        ImcCrud imcCrud = new ImcCrud();
        
        
        
        
        
        

        // Insérer l'IMC dans la base de données
        boolean insertionImcReussie = imcCrud.insertImc(imc);

        if (insertionImcReussie) {
            System.out.println("IMC inséré avec succès dans la base de données.");
        } else {
            System.out.println("Erreur lors de l'insertion de l'IMC dans la base de données.");
        }

        // Créer une instance de Plan en utilisant le constructeur
        Plan plan = new Plan(imc.getIMC(), imc.getPoids(), imc.getTaille(), imc.getSexe(), "modérément actif", "Je suis végétarienne", "Perte de poids", 30);

        // Afficher les besoins caloriques
        plan.afficherBesoinsCaloriques();
    }
}
