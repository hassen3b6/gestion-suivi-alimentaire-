package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class IMCResultController implements Initializable {

    @FXML
    private Label txtimc;
    @FXML
    private Label txtimc1;
    @FXML
    private Label labelimc;
    @FXML
    private Label txtimc14;
    @FXML
    private Button btnPlan;
    @FXML
    private Label lbl12;


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Vous pouvez initialiser des éléments ici si nécessaire
    }

    // Méthode pour définir la valeur de l'étiquette IMC
    public void setIMC(double imc) {
        txtimc.setText(String.valueOf(imc));
    }

    // Méthode pour définir la catégorie IMC
    public void setCategorieIMC(String categorieIMC) {
        txtimc1.setText(categorieIMC);
 
        if (categorieIMC.equals("Poids normal")) {
            btnPlan.setVisible(false);
            lbl12.setVisible(true);
        } else {
            btnPlan.setVisible(true);
            lbl12.setVisible(false);
        }
    }

    // Méthode pour définir le poids actuel
    public void setPoidsActuel(double poidsActuel) {
        labelimc.setText(" " + String.valueOf(poidsActuel));
    }

    // Méthode pour définir le poids idéal
    public void setPoidsIdeal(double poidsIdeal) {
        txtimc14.setText("  " + String.valueOf(poidsIdeal));
    }

    @FXML
    private void Plan(ActionEvent event) {
        // Code pour gérer le bouton Plan
    }
}
