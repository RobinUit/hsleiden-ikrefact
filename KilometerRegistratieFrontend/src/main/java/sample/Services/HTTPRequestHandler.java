package sample.Services;

import com.google.gson.Gson;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import sample.Models.Car;
import sample.Models.Declaration;
import sample.Models.User;
import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;

//Deze klasse is niet gerefactored!
public class HTTPRequestHandler {

    private String url = "http://localhost:8080";

    public void postHandler(String extraUrl, Object object) throws Exception {
        Gson gson = new Gson();
        HttpClient httpClient = HttpClientBuilder.create().build();
        HttpPost post = new HttpPost(url + extraUrl);
        StringEntity postingString = new StringEntity(gson.toJson(object));
        post.setEntity(postingString);
        post.setHeader("Content-type", "application/json");
        HttpResponse response = httpClient.execute(post);
        if (!response.getStatusLine().toString().contains("200")) {
            throw new Exception("");
        }
    }

    public User getUserByMail(String extraUrl){
        try {
            StringBuilder jsonS = new StringBuilder();
            URL url1 = new URL(url + extraUrl);
            URLConnection conn = url1.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) jsonS.append(inputLine);

            Gson gson = new Gson();
            return gson.fromJson(jsonS.toString(), User.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public int getUserIDByEmail(String extraUrl){
        int niks = 0;
        try {
            StringBuilder jsonS = new StringBuilder();
            URL url1 = new URL(url + extraUrl);
            URLConnection conn = url1.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonS.append(inputLine);
            }
            return Integer.parseInt(jsonS.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return niks;
    }

    public Declaration[] getDeclarationsByID(String extraUrl){
        try {
            StringBuilder jsonS = new StringBuilder();
            URL url1 = new URL(url + extraUrl);
            URLConnection conn = url1.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) jsonS.append(inputLine);

            Gson gson = new Gson();
            return gson.fromJson(jsonS.toString(), Declaration[].class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public ArrayList<Car> getCarsByID(String extralUrl){
        try {
            StringBuilder jsonS = new StringBuilder();
            URL url1 = new URL(url + extralUrl);
            URLConnection conn = url1.openConnection();
            conn.connect();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    conn.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                jsonS.append(inputLine);
            }

            Gson gson = new Gson();
            Car[] cars = gson.fromJson(jsonS.toString(), Car[].class);
            return new ArrayList<>(Arrays.asList(cars));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Deletes an element from the database,
     * from the table that you give in the parameters.
     * Needs the id of the element that you want to delete.
     * @param table de naam van de tabel
     * @param id het id van het te verwijderen object
     * @author Tom
     */
    public void removeFromTableById(String table, String id) {
        String removeUrl = url + "/" + table + "/delete/" + id;
        try {
            HttpClient httpClient = HttpClientBuilder.create().build();
            HttpDelete delete = new HttpDelete(removeUrl);
            httpClient.execute(delete);
        } catch (IOException ignored) {
        }
    }

//    private static String convertStreamToString(InputStream is) {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
//        StringBuilder sb = new StringBuilder();
//
//        String line = null;
//        try {
//            while ((line = reader.readLine()) != null) {
//                sb.append(line + "\n");
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                is.close();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return sb.toString();
//    }
}
