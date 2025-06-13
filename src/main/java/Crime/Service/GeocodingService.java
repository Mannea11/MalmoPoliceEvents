package Crime.Service;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Optional;

@Service
public class GeocodingService {

    public Optional<double[]> geocode(String place) {
        try {
            String query = URLEncoder.encode(place + ", Malmö, Sweden", StandardCharsets.UTF_8);
            URL url = new URL("https://nominatim.openstreetmap.org/search?q=" + query + "&format=json&limit=1");

            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("Manne.ande@gmail.com", "MalmoBrott");
            InputStream response = connection.getInputStream();

            String json = new String(response.readAllBytes(), StandardCharsets.UTF_8);
            JSONArray arr = new JSONArray(json);
            if (arr.length() == 0) return Optional.empty();

            JSONObject loc = arr.getJSONObject(0);
            return Optional.of(new double[] {
                    loc.getDouble("lat"),
                    loc.getDouble("lon")
            });

        } catch (Exception e) {
            return Optional.empty();
        }
    }
}
