package com.javacourse.Calculations;

public class UnsuccessfulExpressionProcessingException extends Exception {

    public UnsuccessfulExpressionProcessingException() {
    }

    public UnsuccessfulExpressionProcessingException(String message) {
        super(message);
    }

    public UnsuccessfulExpressionProcessingException(Throwable cause) {
        super(cause);
    }
}
