package com.javacourse.Calculations;

public class OperationNotSupportedException extends RuntimeException {

    public OperationNotSupportedException() {
    }

    public OperationNotSupportedException(String message) {
        super(message);
    }
}
