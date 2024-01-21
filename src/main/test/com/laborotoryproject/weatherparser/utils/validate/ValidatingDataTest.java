package com.laborotoryproject.weatherparser.utils.validate;

import org.junit.jupiter.api.Test;

import static com.laborotoryproject.weather.parser.util.validate.ValidatingData.*;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatingDataTest {


    @Test
    public void testValidateTemperature() {

        assertTrue(validateTemperature("25"));
        assertTrue(validateTemperature("-10"));
        assertTrue(validateTemperature("0"));
        assertTrue(validateTemperature("12.5"));
        assertTrue(validateTemperature("-3.75"));

        assertFalse(validateTemperature("abc"));
        assertFalse(validateTemperature("1a"));
        assertFalse(validateTemperature(""));
        assertFalse(validateTemperature("0.0.0"));
        assertFalse(validateTemperature("-"));
    }

    @Test
    public void testValidatePressure() {
        assertTrue(validatePressure("12 мм"));
        assertTrue(validatePressure("1 мм"));


        assertFalse(validatePressure("мм"));
        assertFalse(validatePressure("12мм"));
        assertFalse(validatePressure("мм21"));
        assertFalse(validatePressure("мм "));
        assertFalse(validatePressure("12 ab"));
    }

    @Test
    public void testValidateHumidity() {
        assertTrue(validateHumidity("12"));
        assertTrue(validateHumidity("6"));

        assertFalse(validateHumidity("ab"));
        assertFalse(validateHumidity("adc12"));
        assertFalse(validateHumidity("21adc"));
        assertFalse(validateHumidity("adc "));
        assertFalse(validateHumidity("12 adc"));
    }

    @Test
    public void testValidateSpeed() {
        assertTrue(validateSpeed("12 мс"));
        assertTrue(validateSpeed("1 мс"));

        assertFalse(validateSpeed("12"));
        assertFalse(validateSpeed("12мс"));
        assertFalse(validateSpeed("мс12"));
        assertFalse(validateSpeed("12 "));
        assertFalse(validateSpeed("12 ab"));
    }

    @Test
    public void testValidateCityName() {
        assertTrue(validateCityName("moscow"));

        assertFalse(validateCityName("123"));
        assertFalse(validateCityName("ab12"));
        assertFalse(validateCityName("21ab"));
        assertFalse(validateCityName("ab 12"));
    }
}
