package GUI;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HomePage extends Application {

    @Override
    public void start(Stage stage) {
        try {
            // Charge le fichier FXML
            FXMLLoader loader = new FXMLLoader(getClass().getResource("CalculerImc.fxml"));
            Parent root = loader.load();

            // Crée la scène
            Scene scene = new Scene(root);

            // Configure la scène et affiche la fenêtre
            stage.setScene(scene);
            stage.setTitle("FitNatic");
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
