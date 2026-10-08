package no.sofiya.aurora;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ForecastController {

    private static final int HOURS = 8;

    private final NoaaClient noaa;
    private final MetClient met;
    private final ChanceCalculator calculator;

    public ForecastController(NoaaClient noaa, MetClient met, ChanceCalculator calculator) {
        this.noaa = noaa;
        this.met = met;
        this.calculator = calculator;
    }

    @GetMapping("/cities")
    public List<Map<String, String>> cities() {
        List<Map<String, String>> list = new ArrayList<>();
        for (City city : City.values()) {
            list.add(Map.of("id", city.name().toLowerCase(), "name", city.getDisplayName()));
        }
        return list;
    }

    @GetMapping("/forecast/{id}")
    public Forecast forecast(@PathVariable String id) {
        City city = City.fromId(id);
        double kp = noaa.latestKp();
        List<MetClient.HourlyCloud> clouds = met.cloudsForNextHours(city, HOURS);

        List<Forecast.Hour> hours = new ArrayList<>();
        for (MetClient.HourlyCloud hour : clouds) {
            Chance chance = calculator.calculate(city, kp, hour.cloudPercent());
            hours.add(new Forecast.Hour(hour.time(), hour.cloudPercent(), chance));
        }

        double cloudsNow = clouds.get(0).cloudPercent();
        Chance chanceNow = calculator.calculate(city, kp, cloudsNow);
        return new Forecast(city.getDisplayName(), kp, city.getMinKp(), cloudsNow, chanceNow, hours);
    }
}
