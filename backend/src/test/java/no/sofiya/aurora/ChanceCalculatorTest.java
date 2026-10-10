package no.sofiya.aurora;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class ChanceCalculatorTest {

    private final ChanceCalculator calculator = new ChanceCalculator();

    @Test
    void cloudyIsAlwaysNotTonight() {
        assertEquals(Chance.NOT_TONIGHT, calculator.calculate(City.TROMSO, 9, 90));
    }

    @Test
    void strongAuroraAndClearSkyIsGood() {
        assertEquals(Chance.GOOD, calculator.calculate(City.TROMSO, 4, 10));
    }

    @Test
    void justEnoughIsMaybe() {
        assertEquals(Chance.MAYBE, calculator.calculate(City.BERGEN, 3, 10));
    }

    @Test
    void strongButSomeCloudsIsMaybe() {
        assertEquals(Chance.MAYBE, calculator.calculate(City.TROMSO, 5, 55));
    }

    @Test
    void tooWeakForTheSouthIsNotTonight() {
        assertEquals(Chance.NOT_TONIGHT, calculator.calculate(City.OSLO, 3, 0));
    }
}
