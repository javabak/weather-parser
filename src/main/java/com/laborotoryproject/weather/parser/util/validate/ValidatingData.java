package com.laborotoryproject.weather.parser.util.validate;

import java.util.regex.Pattern;

public class ValidatingData {

    public static boolean validateTemperature(String temperature) {
        String regex = "^-?\\d+(\\.\\d+)?$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(temperature).matches()
               || (temperature.startsWith("-") && pattern.matcher(temperature).matches())
               || (temperature.startsWith("0") && pattern.matcher(temperature).matches());
    }

    public static boolean validateCityName(String string) {
        String regex = "^\\D*$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(string.trim()).find();
    }

    public static boolean validatePressure(String pressure) {
        String regex = "^\\d+\\sмм$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(pressure.trim()).matches();
    }

    public static boolean validateSpeed(String speed) {
        String regex = "^\\d+\\sмс$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(speed.trim()).matches();
    }

    public static boolean validateHumidity(String humidity) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(humidity.trim()).matches();
    }

    public static boolean validateId(Long id) {
        String regex = "^\\d+$";
        Pattern pattern = Pattern.compile(regex);
        return pattern.matcher(String.valueOf(id).trim()).matches();
    }
}
