package com.laborotoryproject.weather.parser.util.validate;

import java.util.regex.Pattern;

public class ValidatingData {


    public boolean checkTemperatureInput(String string) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string).find() || string.startsWith("-") || string.startsWith("0");
    }

    public boolean checkStringNotContainsDigit(String string) {
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        return !pattern.matcher(string.trim()).find();
    }

    public boolean checkStringStartWithDigitAndContainsLetter(String string) {
        String regex = "^[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string.trim()).find();
    }
}
