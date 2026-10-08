package no.sofiya.aurora;

import java.util.List;

public record Forecast(
        String city,
        double kp,
        int minKp,
        double cloudPercent,
        Chance chance,
        List<Hour> hours) {

    public record Hour(String time, double cloudPercent, Chance chance) {
    }
}
