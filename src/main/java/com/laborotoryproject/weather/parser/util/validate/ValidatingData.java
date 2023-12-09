package com.laborotoryproject.weather.parser.util.validate;

import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringContainsDigitException;
import com.laborotoryproject.weather.parser.exception.validate_exceptions.StringNotStartWithDigitException;
import org.springframework.stereotype.Component;

import java.util.regex.Pattern;

@Component
public class ValidatingData {


    public boolean checkTemperatureInput(String string) {
        String regex = "\\d+";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(string).find() || string.startsWith("-") || string.startsWith("0")) {
            return true;
        } else {
            throw new StringNotStartWithDigitException("string does not start with '+' or '-'");
        }
    }

    public boolean checkStringNotContainsDigit(String string) {
        String regex = "[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        if (!pattern.matcher(string.trim()).find()) {
            return true;
        } else {
            throw new StringContainsDigitException("string contains digit");
        }
    }

    public boolean checkStringStartWithAndContainsDigit(String string) {
        String regex = "^[0-9]+";
        Pattern pattern = Pattern.compile(regex);
        if (pattern.matcher(string.trim()).find()) {
            return true;
        } else {
            throw new StringNotStartWithDigitException("string does not start with digit");
        }
    }
}
