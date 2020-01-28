package sample.Services;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import javafx.scene.image.WritableImage;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.imageio.ImageIO;
import java.io.IOException;
import java.net.URL;

public class GoogleMapsHandler {

    private Response response;
    private String requestUrl;
    private String parameters;
    private WritableImage map = null;
    private String[][] originDestinationDistance;
    private JSONObject responseInJSONObject;
    private String[] originAddress;
    private String[] destinationAddress;
    private String[] distanceBetween;


    public String[][] getOriginDestinationAddressAndDistanceBetween(String origin, String destination) {
        parameters =
                "origins=" + origin +
                        "&destinations=" + destination;

        buildRequestUrl("distancematrix/json", parameters);

        buildAndSendRequest(requestUrl);

        handleResponse();

        return originDestinationDistance;
    }

    private void buildRequestUrl(String apiName, String parameters) {
        String API_KEY = "AIzaSyCG2jL_rZO0BnhsjSFxvKq39Okq2GZNf98";
        requestUrl = "https://maps.googleapis.com/maps/api/" +
                apiName +
                "?" + parameters +
                "&key=" + API_KEY +
                "&language=nl";
    }

    private void buildAndSendRequest(String requestUrl) {
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder()
                    .url(requestUrl)
                    .build();
            response = client.newCall(request).execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void handleResponse() {
        convertResponseToJSON();

        extractOriginAddress();
        extractDestinationAddress();
        extractDistanceBetween();

        originDestinationDistance = new String[][]{
                originAddress, destinationAddress, distanceBetween
        };
    }

    private void convertResponseToJSON() {
        try {
            assert response.body() != null;
            responseInJSONObject = new JSONObject(response.body().string());
        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }

    private void extractOriginAddress() {
        JSONArray originAddressJSONArray = (JSONArray) responseInJSONObject.get("origin_addresses");
        originAddress = toVariables((String) originAddressJSONArray.get(0));
    }

    private void extractDestinationAddress() {
        JSONArray destinationAddressJSONArray = (JSONArray) responseInJSONObject.get("destination_addresses");
        destinationAddress = toVariables((String) destinationAddressJSONArray.get(0));
    }

    private void extractDistanceBetween() {
        JSONArray dist = (JSONArray) responseInJSONObject.get("rows");
        JSONObject obj2 = (JSONObject) dist.get(0);
        JSONArray disting = (JSONArray) obj2.get("elements");
        JSONObject obj3 = (JSONObject) disting.get(0);
        JSONObject obj4 = (JSONObject) obj3.get("distance");
        String kmplustext = (String) obj4.get("text");
        String km = kmplustext.split(" ")[0];

        distanceBetween = new String[]{km};
    }

    private String[] toVariables(String address) {
        try {
            String[] temp;
            String delimiter = ",";

            temp = address.split(delimiter);
            String[] ar = new String[5];

            int index = temp[0].lastIndexOf(" ");
            String straat = temp[0].substring(0, index);
            String huisnummer = temp[0].substring(index + 1);

            if (temp.length == 4) {
                ar[0] = straat.trim(); //straatnaam
                ar[1] = huisnummer.trim(); //huisnummer
                ar[2] = temp[2].substring(0, 8).trim(); //postcode
                ar[3] = temp[2].substring(9).trim(); //plaatsnaam
                ar[4] = temp[3].trim(); //country
            } else if (temp.length == 3) {
                ar[0] = straat.trim(); //straatnaam
                ar[1] = huisnummer.trim(); //huisnummer
                ar[2] = temp[1].substring(0, 8).trim(); //postcode
                ar[3] = temp[1].substring(9).trim(); //plaatsnaam
                ar[4] = temp[2].trim(); //country
            }
            return ar;
        } catch (Exception e) {
            return null;
        }
    }

    public Image getRouteMap(String originAddress, String destinationAddress) {
        parameters =
                "size=900x320&maptype=roadmap" +
                        "&markers=size:mid%7Ccolor:red%7C" +
                        originAddress.replaceAll(" ", "") + "%7C" +
                        destinationAddress.replaceAll(" ", "");

        handleRequest();

        return map;
    }

    public Image getDefaultMap() {
        parameters =
                "center=Netherlands" +
                        "&zoom=6" +
                        "&size=900x320" +
                        "&maptype=roadmap";

        handleRequest();

        return map;
    }

    private void handleRequest() {
        buildRequestUrl("staticmap", parameters);
        getImageFromUrl();
    }

    private void getImageFromUrl() {
        try {
            map = SwingFXUtils.toFXImage(
                    ImageIO.read(
                            new URL(requestUrl)), null);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
