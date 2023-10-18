/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package api;


import java.io.BufferedReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

public class SpoonacularAPI {
    private static final String API_KEY = "26d449acd3784127880222272dfc0135";
    private static final String IMC_API_ENDPOINT = "https://api.spoonacular.com/recipes/search/imc"; // Assurez-vous que l'API réelle existe.

    public static String sendIMCToSpoonacular(double imc) {
        try {
            HttpClient httpClient = HttpClients.createDefault();
            HttpPost httpPost = new HttpPost(IMC_API_ENDPOINT);

            // Créez un objet JSON contenant l'IMC
            JSONObject json = new JSONObject();
            json.put("imc", imc);

            // Convertissez l'objet JSON en une chaîne JSON
            String jsonBody = json.toString();

            StringEntity entity = new StringEntity(jsonBody);
            httpPost.setEntity(entity);
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Authorization", "Bearer " + API_KEY);

            HttpResponse response = httpClient.execute(httpPost);
            HttpEntity responseEntity = response.getEntity();

            if (responseEntity != null) {
                return EntityUtils.toString(responseEntity);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
