package com.laborotoryproject.weather.parser.exception.validate_exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class StringNotStartWithDigitException extends RuntimeException {
    public StringNotStartWithDigitException(String message) {
        super(message);
    }
}
