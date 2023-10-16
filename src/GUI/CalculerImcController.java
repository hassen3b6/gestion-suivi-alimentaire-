package GUI;

import entities.Imc;
import services.ImcCrud;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXMLLoader;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class CalculerImcController implements Initializable {

    @FXML
    private TextField txtnom1;
    @FXML
    private TextField txtnom11;
    @FXML
    private Button btnCalculer;
    @FXML
    private ComboBox<String> combo_sexe;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_sexe.getItems().addAll("Homme", "Femme");
    }

    @FXML
    private void Calculer1(ActionEvent event) {
        System.out.println("Bouton Calculer1 cliqué."); // Message de débogage

        // Le reste de votre code ici...
        String sexe = combo_sexe.getValue();
        double taille, poids;

        if (sexe == null || sexe.isEmpty()) {
            showErrorAlert("Veuillez choisir le sexe (Homme ou Femme).");
            return;
        }

        try {
            taille = Double.parseDouble(txtnom1.getText());
            poids = Double.parseDouble(txtnom11.getText());

            if (taille <= 0 || poids <= 0) {
                // Afficher un message d'erreur si la saisie est invalide
                showErrorAlert("La taille et le poids doivent être des valeurs positives.");
                return;
            }
        } catch (NumberFormatException e) {
            // Afficher un message d'erreur si la saisie est incorrecte
            showErrorAlert("Erreur de format : Assurez-vous de saisir des nombres valides.");
            return;
        }

        Imc personne = new Imc();
        personne.setSexe(sexe);
        personne.setTaille(taille);
        personne.setPoids(poids);

        // Calcul de l'IMC
        personne.calculerIMC();

        // Calcul du poids idéal
        personne.calculerPoidsIdeal();

        double imc = personne.getIMC();
        double poidsActuel = personne.getPoids();
        double poidsIdeal = personne.getPoidsIdeal();
        String categorieIMC = personne.getCategorieIMC();

        // Insérer les données IMC dans la base de données en utilisant ImcCrud
        if (ImcCrud.insertImc(personne)) {
            System.out.println("Données IMC insérées avec succès dans la base de données.");
        } else {
            System.out.println("Erreur lors de l'insertion des données IMC dans la base de données.");
        }

        // Code pour afficher la nouvelle fenêtre avec les résultats (à ajouter)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("IMCResult.fxml"));
            Parent root = loader.load();

            IMCResultController resultController = loader.getController();
            resultController.setIMC(imc);
            resultController.setCategorieIMC(categorieIMC);
            resultController.setPoidsActuel(poidsActuel); // Nouvelle ligne
            resultController.setPoidsIdeal(poidsIdeal);   // Nouvelle ligne

            Stage stage = new Stage();
            stage.setTitle("Résultats IMC");
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showErrorAlert(String message) {
        Alert alert = new Alert(AlertType.ERROR);
        alert.setTitle("Erreur");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
