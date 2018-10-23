package com.javacourse;

public class TokenNotSupportedException extends RuntimeException {

    public TokenNotSupportedException() {
    }

    public TokenNotSupportedException(String message) {
        super(message);
    }
}
