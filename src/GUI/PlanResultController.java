// PlanResultController.java
package GUI;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class PlanResultController implements Initializable {

    @FXML
    private Label txtc;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    // Méthode pour définir les besoins caloriques
    public void setBesoinsCaloriques(double besoinsCaloriques) {
        txtc.setText(String.valueOf(besoinsCaloriques));
    }
}
