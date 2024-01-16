package com.laborotoryproject.weather.parser.util.validate;

import java.util.regex.Pattern;

public class ValidatingData {

    public boolean checkTemperatureInput(String string) {
        String regex = "^-?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string).matches()
               || (string.startsWith("-") && pattern.matcher(string).matches())
               || (string.startsWith("0") && pattern.matcher(string).matches());
    }

    public boolean checkStringNotContainsDigit(String string) {
        String regex = "^[^\\d]*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string.trim()).find();
    }

    public boolean checkStringStartWithDigitAndContainsLetter(String string) {
        String regex = "^\\d.*[a-zA-Zа-яА-Я]";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string.trim()).find();
    }
}
