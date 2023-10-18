/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;

/**
 *
 * @author hassen
 */
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class SpoonacularAPI {
    private static final String API_KEY = "26d449acd3784127880222272dfc0135"; // Remplacez par votre nouvelle clé API
    private static final String BASE_URL = "https://api.spoonacular.com/recipes/search";

    public static String searchRecipes(String query) {
        try {
            URL url = new URL(BASE_URL + "?apiKey=" + API_KEY + "&query=" + query);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String line;
            StringBuilder response = new StringBuilder();

            while ((line = reader.readLine()) != null) {
                response.append(line);
            }

            reader.close();
            connection.disconnect();

            return response.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
