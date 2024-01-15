package com.laborotoryproject.weatherparser.utils.validate;

import org.junit.jupiter.api.Test;

import java.util.regex.Pattern;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ValidatingDataTest {

    @Test
    public void testCheckTemperatureInput_1() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("12").matches();

        assertTrue(b);
    }

    @Test
    public void testCheckTemperatureInput_2() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("abc").matches();

        assertFalse(b);
    }

    @Test
    public void testCheckTemperatureInput_3() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("-12").matches();

        assertTrue(b);
    }

    @Test
    public void testCheckTemperatureInput_4() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("-abc").matches();

        assertFalse(b);
    }

    @Test
    public void testCheckTemperatureInput_5() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("0").matches();

        assertTrue(b);
    }

    @Test
    public void testCheckTemperatureInput_6() {
        String regex = "^-?\\d+(\\.\\d+)?";
        Pattern pattern = Pattern.compile(regex);

        boolean b = pattern.matcher("0asd").matches();

        assertFalse(b);
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
