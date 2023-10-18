package GUI;

import entities.Plan;
import Services.PlanCrud;
import utils.MyConnection;
import java.io.IOException;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class PlanController implements Initializable {
    @FXML
    private ChoiceBox<String> combo_obj;
    @FXML
    private ChoiceBox<String> combo_activ;
    @FXML
    private ChoiceBox<String> combo_reg;
    @FXML
    private Button btnobtenir;
    @FXML
    private TextField txtage;
    @FXML
    private TextField txtPoids;
    @FXML
    private TextField txtTaille;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        combo_activ.getItems().addAll("légèrement actif", "modérément actif", "très actif", "extrêmement actif");
        combo_obj.getItems().addAll("Perte de poids", "Tonifier les muscles");
        combo_reg.getItems().addAll("Je suis intolérante au lactose", "Je ne mange pas de gluten", "Je suis végétarienne");
    }

    @FXML
    private void obtenirbesoincaloriques(ActionEvent event) {
        String objectif = combo_obj.getValue();
        String niveauActivite = combo_activ.getValue();
        String regime = combo_reg.getValue();
        int age;
        double poids, taille;

        if (objectif == null || niveauActivite == null || regime == null || txtage.getText().isEmpty() || txtPoids.getText().isEmpty() || txtTaille.getText().isEmpty()) {
            showErrorAlert("Veuillez remplir tous les champs.");
            return;
        }

        try {
            age = Integer.parseInt(txtage.getText());
            poids = Double.parseDouble(txtPoids.getText());
            taille = Double.parseDouble(txtTaille.getText());
        } catch (NumberFormatException e) {
            showErrorAlert("L'âge, le poids et la taille doivent être des nombres valides.");
            return;
        }

        Plan plan = new Plan(poids, taille, niveauActivite, regime, objectif, age);

        // Calcul des besoins caloriques
        double besoinsCaloriques = plan.calculerBesoinsCaloriques();

        if (PlanCrud.insertPlan(plan)) {
            System.out.println("Données du plan insérées avec succès dans la base de données.");
        } else {
            System.out.println("Erreur lors de l'insertion des données du plan dans la base de données.");
        }

        // Code pour afficher la nouvelle fenêtre avec les résultats (à ajouter)
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("PlanResult.fxml"));
            Parent root = loader.load();

            PlanResultController resultController = loader.getController();
            resultController.setBesoinsCaloriques(besoinsCaloriques);

            Stage stage = new Stage();
            stage.setTitle("Résultats Plan");
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
