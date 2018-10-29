package com.javacourse;

public class TariffNotSupportedException extends RuntimeException {
    public TariffNotSupportedException() {
    }

    public TariffNotSupportedException(String message) {
        super(message);
    }
}
