package no.sofiya.aurora;

import org.springframework.stereotype.Component;

@Component
public class ChanceCalculator {

    public Chance calculate(City city, double kp, double cloudPercent) {
        // too cloudy to see anything
        if (cloudPercent > 70) {
            return Chance.NOT_TONIGHT;
        }
        if (kp >= city.getMinKp() + 1 && cloudPercent <= 40) {
            return Chance.GOOD;
        }
        if (kp >= city.getMinKp()) {
            return Chance.MAYBE;
        }
        return Chance.NOT_TONIGHT;
    }
}
