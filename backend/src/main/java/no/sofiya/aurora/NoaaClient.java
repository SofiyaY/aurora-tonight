package no.sofiya.aurora;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

// Latest Kp index, fetched from NOAA Space Weather Prediction Center (swpc.noaa.gov)
@Component
public class NoaaClient {

    private static final String URL = "https://services.swpc.noaa.gov/products/noaa-planetary-k-index.json";

    private final RestClient http = RestClient.create();

    public double latestKp() {
        // newest reading is last in the list
        JsonNode readings = http.get().uri(URL).retrieve().body(JsonNode.class);
        JsonNode newest = readings.get(readings.size() - 1);
        return newest.get("Kp").asDouble();
    }
}
