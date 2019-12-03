package sample.Services;

import javafx.embed.swing.SwingFXUtils;
import javafx.scene.image.Image;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;

public class GoogleMapsHandler {

    private String API_KEY = "AIzaSyCG2jL_rZO0BnhsjSFxvKq39Okq2GZNf98";

    public String[][] calculate(String origin , String destination) throws IOException {
        OkHttpClient client = new OkHttpClient();
        String url="https://maps.googleapis.com/maps/api/distancematrix/json?origins="+origin+"&destinations="+destination+"&key="+ API_KEY;
        Request request = new Request.Builder()
                .url(url)
                .build();

        Response response = client.newCall(request).execute();

        try {
            assert response.body() != null;
            JSONObject jsonobj = new JSONObject(response.body().string());

            //begin address
            JSONArray begin=(JSONArray)jsonobj.get("origin_addresses");
            String[] originAddress = toVariables((String) begin.get(0));

            //end address
            JSONArray end=(JSONArray)jsonobj.get("destination_addresses");
            String[] destinationAddress = toVariables((String) end.get(0));

            String[] distance;

            //distance
            try {
                JSONArray dist = (JSONArray) jsonobj.get("rows");
                JSONObject obj2 = (JSONObject) dist.get(0);
                JSONArray disting = (JSONArray) obj2.get("elements");
                JSONObject obj3 = (JSONObject) disting.get(0);
                JSONObject obj4 = (JSONObject) obj3.get("distance");
                String kmplustext = (String) obj4.get("text");
                String km = kmplustext.split(" ")[0];
                distance = new String[] {km};

            } catch (Exception e) {
                return null;
            }

            return new String[][] {originAddress, destinationAddress, distance};
        }
        catch(Exception e) {
            e.printStackTrace();
        }
        return null;
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

    public Image maps(String origin, String destination) {
        try {
            BufferedImage image = ImageIO.read(new URL(
                    "https://maps.googleapis.com/maps/api/staticmap?size=900x320&maptype=roadmap" +
                    "&markers=size:mid%7Ccolor:red%7C" +
                            origin.replaceAll(" ", "") + "%7C" +
                            destination.replaceAll(" ", "")
                            + "&key=" + API_KEY
                            + "&language=nl"));
            return SwingFXUtils.toFXImage(image, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public Image defaultMap() {
        try {
            BufferedImage image = ImageIO.read(new URL(
                    "https://maps.googleapis.com/maps/api/staticmap?center=Netherlands&zoom=6&size=900x320&maptype=roadmap"
                            + "&key=" + API_KEY
                            + "&language=nl"));
            return SwingFXUtils.toFXImage(image, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
