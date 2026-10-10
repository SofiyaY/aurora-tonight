package no.sofiya.aurora;

import com.fasterxml.jackson.databind.JsonNode;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import java.util.ArrayList;
import java.util.List;

// Cloud forecast, fetched from MET Norway (api.met.no), the same data as Yr
@Component
public class MetClient {

    private static final String URL = "https://api.met.no/weatherapi/locationforecast/2.0/compact?lat={lat}&lon={lon}";

    // MET requires a User-Agent
    private final RestClient http = RestClient.builder()
            .defaultHeader("User-Agent", "aurora-tonight/1.0 github.com/SofiyaY")
            .build();

    public List<HourlyCloud> cloudsForNextHours(City city, int hours) {
        JsonNode body = http.get()
                .uri(URL, city.getLatitude(), city.getLongitude())
                .retrieve()
                .body(JsonNode.class);

        JsonNode timeseries = body.get("properties").get("timeseries");
        List<HourlyCloud> result = new ArrayList<>();
        for (int i = 0; i < hours && i < timeseries.size(); i++) {
            JsonNode hour = timeseries.get(i);
            String time = hour.get("time").asText();
            double clouds = hour.get("data").get("instant").get("details").get("cloud_area_fraction").asDouble();
            result.add(new HourlyCloud(time, clouds));
        }
        return result;
    }

    public record HourlyCloud(String time, double cloudPercent) {
    }
}
