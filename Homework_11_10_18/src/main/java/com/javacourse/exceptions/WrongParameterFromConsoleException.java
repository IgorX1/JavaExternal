package com.javacourse.exceptions;

public class WrongParameterFromConsoleException extends RuntimeException {

    public WrongParameterFromConsoleException() {
        super();
    }

    public WrongParameterFromConsoleException(String message) {
        super(message);
    }
}
