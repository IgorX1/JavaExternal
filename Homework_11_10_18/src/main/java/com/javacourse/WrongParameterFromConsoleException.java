package com.javacourse;

public class WrongParameterFromConsoleException extends RuntimeException {

    public WrongParameterFromConsoleException() {
        super();
    }

    public WrongParameterFromConsoleException(String message) {
        super(message);
    }
}
