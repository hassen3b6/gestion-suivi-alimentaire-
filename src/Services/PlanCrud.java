package Services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import entities.Plan;
import utils.MyConnection;

public class PlanCrud {

    public static boolean insertPlan(Plan plan) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    private Connection connection;

    public PlanCrud(Connection connection) {
        this.connection = connection;
    }

   public boolean insertBesoinsCaloriques(int imcId, String niveauActivite, String regime, String objectif, int age) throws SQLException {
    if (connection == null) {
        throw new SQLException("La connexion à la base de données n'est pas initialisée.");
    }

    try {
        // Récupérez les données IMC, taille, poids et sexe de la table "imc" en fonction de l'ID
        String selectImcQuery = "SELECT IMC, taille, poids, sexe FROM imc WHERE id = ?";

        PreparedStatement selectStatement = connection.prepareStatement(selectImcQuery);
        selectStatement.setInt(1, imcId);
        ResultSet resultSet = selectStatement.executeQuery();

        if (resultSet.next()) {
            double imc = resultSet.getDouble("IMC");
            double taille = resultSet.getDouble("taille");
            double poids = resultSet.getDouble("poids");
            String sexe = resultSet.getString("sexe");

            // Créez une instance de Plan
            Plan plan = new Plan(imc, poids, taille, sexe, niveauActivite, regime, objectif, age);

            // Calcul des besoins caloriques en utilisant la méthode de Plan
            double besoinsCaloriques = plan.calculerBesoinsCaloriques();

            // Insérez les besoins caloriques dans la table "plan_alimentaire"
            String insertQuery = "INSERT INTO plan_alimentaire (imc_id, niveau_activite, regime, objectif, age, besoins_caloriques) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement insertStatement = connection.prepareStatement(insertQuery);
            insertStatement.setInt(1, imcId);
            insertStatement.setString(2, niveauActivite);
            insertStatement.setString(3, regime);
            insertStatement.setString(4, objectif);
            insertStatement.setInt(5, age);
            insertStatement.setDouble(6, besoinsCaloriques);

            int rowsAffected = insertStatement.executeUpdate();

            if (rowsAffected > 0) {
                return true;
            } else {
                throw new SQLException("Échec de l'insertion dans la table plan_alimentaire.");
            }
        } else {
            throw new SQLException("Aucune donnée IMC trouvée pour l'ID spécifié.");
        }
    } catch (SQLException e) {
        throw e;
    }
}

}

