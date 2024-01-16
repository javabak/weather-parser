package com.laborotoryproject.weatherparser.utils.validate;

import com.laborotoryproject.weather.parser.util.validate.ValidatingData;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatingDataTest {

    ValidatingData validatingData = new ValidatingData();

    @Test
    public void testCheckTemperatureInput() {

        // Valid Inputs
        assertTrue(validatingData.checkTemperatureInput("25"));
        assertTrue(validatingData.checkTemperatureInput("-10"));
        assertTrue(validatingData.checkTemperatureInput("0"));
        assertTrue(validatingData.checkTemperatureInput("12.5"));
        assertTrue(validatingData.checkTemperatureInput("-3.75"));

        // Invalid inputs
        assertFalse(validatingData.checkTemperatureInput("abc"));
        assertFalse(validatingData.checkTemperatureInput("1a"));
        assertFalse(validatingData.checkTemperatureInput(""));
        assertFalse(validatingData.checkTemperatureInput("0.0.0"));
        assertFalse(validatingData.checkTemperatureInput("-"));
    }

    @Test
    public void testCheckStringNotContainsDigit() {
        assertTrue(validatingData.checkStringNotContainsDigit("abc"));

        assertFalse(validatingData.checkStringNotContainsDigit("23asd"));
    }

    @Test
    public void testCheckStringStartWithDigitAndContainsLetter() {
        assertTrue(validatingData.checkStringStartWithDigitAndContainsLetter("123 dad"));

        assertFalse(validatingData.checkStringStartWithDigitAndContainsLetter("123"));
    }

    @Test
    public void testCheckStringForHumidity() {
        assertTrue(validatingData.checkStringForHumidity("53%"));

        assertFalse(validatingData.checkStringForHumidity("21"));
    }
}
