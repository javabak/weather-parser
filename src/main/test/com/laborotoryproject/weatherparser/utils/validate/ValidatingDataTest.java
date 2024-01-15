package com.laborotoryproject.weatherparser.utils.validate;

import com.laborotoryproject.weather.parser.util.validate.ValidatingData;
import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatingDataTest {

    @Test
    public void testCheckTemperatureInput() {
        ValidatingData temperatureValidator = new ValidatingData();

        // Valid Inputs
        assertTrue(temperatureValidator.checkTemperatureInput("25"));
        assertTrue(temperatureValidator.checkTemperatureInput("-10"));
        assertTrue(temperatureValidator.checkTemperatureInput("0"));
        assertTrue(temperatureValidator.checkTemperatureInput("12.5"));
        assertTrue(temperatureValidator.checkTemperatureInput("-3.75"));

        // Invalid inputs
        assertFalse(temperatureValidator.checkTemperatureInput("abc"));
        assertFalse(temperatureValidator.checkTemperatureInput("1a"));
        assertFalse(temperatureValidator.checkTemperatureInput(""));
        assertFalse(temperatureValidator.checkTemperatureInput("0.0.0"));
        assertFalse(temperatureValidator.checkTemperatureInput("-"));
    }

    @Test
    public void testCheckStringNotContainsDigit_WithValidData() {
        String regex = "^[^\\d]*$";
        Pattern pattern = Pattern.compile(regex);
        boolean b = pattern.matcher("abc".trim()).find();

        assertTrue(b);
    }

    @Test
    public void testCheckStringNotContainsDigit_WithInvalidData() {
        String regex = "^[^\\d]*$";
        Pattern pattern = Pattern.compile(regex);
        boolean b = pattern.matcher("k123jh".trim()).find();

        assertFalse(b);
    }

    @Test
    public void testCheckStringStartWithDigitAndContainsLetter_WithValidData() {
        String regex = "^\\d.*[a-zA-Z]";
        Pattern pattern = Pattern.compile(regex);
        boolean b = pattern.matcher("123asd".trim()).matches();

        assertTrue(b);
    }

    @Test
    public void testCheckStringStartWithDigitAndContainsLetter_WithInvalidData() {
        String regex = "^\\d.*[a-zA-Z]";
        Pattern pattern = Pattern.compile(regex);
        boolean b = pattern.matcher("asd".trim()).matches();

        assertFalse(b);
    }
}
