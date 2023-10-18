package Services;

import entities.Imc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import utils.MyConnection;

public class ImcCrud {

    public static boolean insertImc(Imc imc) {
        Connection conn = MyConnection.getConnection();
        PreparedStatement pstmt = null;

        try {
            String insertSQL = "INSERT INTO imc (sexe, taille, poids, imc, poidsIdeal, categorieIMC) VALUES (?, ?, ?, ?, ?, ?)";

            pstmt = conn.prepareStatement(insertSQL);
            pstmt.setString(1, imc.getSexe());
            pstmt.setDouble(2, imc.getTaille());
            pstmt.setDouble(3, imc.getPoids());
            pstmt.setDouble(4, imc.getIMC());
            pstmt.setObject(5, imc.getPoidsIdeal());  // Utilisation de setObject pour gérer les valeurs null
            pstmt.setString(6, imc.getCategorieIMC());
            int rowsAffected = pstmt.executeUpdate();
            pstmt.close();
            return rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return false;
    }

    public static List<Imc> getAllImc() {
        Connection conn = MyConnection.getConnection();
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        List<Imc> imcList = new ArrayList<>();

        try {
            String selectSQL = "SELECT * FROM imc";
            pstmt = conn.prepareStatement(selectSQL);
            rs = pstmt.executeQuery();

            while (rs.next()) {
                Imc imc = new Imc();
                imc.setId(rs.getInt("id"));
                imc.setSexe(rs.getString("sexe"));
                imc.setTaille(rs.getDouble("taille"));
                imc.setPoids(rs.getDouble("poids"));
                imc.setIMC(rs.getDouble("imc"));
                imc.setPoidsIdeal(rs.getDouble("poidsIdeal"));
                imc.setCategorieIMC(rs.getString("categorieIMC"));
                imcList.add(imc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return imcList;
    }
}
