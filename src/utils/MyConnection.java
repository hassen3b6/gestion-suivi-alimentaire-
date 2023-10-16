package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class MyConnection {
    // Informations de connexion à la base de données
    private static final String URL = "jdbc:mysql://localhost:3306/gest1"; // Remplacez "gest1" par le nom de votre base de données
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    
    private static Connection connection;

    // Méthode privée pour établir la connexion à la base de données
    private static Connection createConnection() {
        try {
            Connection conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            System.out.println("Connexion établie!");
            return conn;
        } catch (SQLException ex) {
            // En cas d'erreur de connexion, afficher le message d'erreur
            System.err.println("Erreur de connexion : " + ex.getMessage());
            return null;
        }
    }

    // Méthode pour obtenir l'instance de connexion à la base de données
    public static Connection getConnection() {
        if (connection == null) {
            connection = createConnection();
        }
        return connection;
    }

    // Méthode pour fermer la connexion à la base de données
    public static void closeConnection() {
        if (connection != null) {
            try {
                connection.close();
                System.out.println("Connexion fermée.");
            } catch (SQLException ex) {
                System.err.println("Erreur lors de la fermeture de la connexion : " + ex.getMessage());
            } finally {
                connection = null;
            }
        }
    }
}
