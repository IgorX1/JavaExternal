package com.javacourse;

public class ProgramShouldBeTerminatedException extends RuntimeException {

    public ProgramShouldBeTerminatedException() {
    }

    public ProgramShouldBeTerminatedException(String message) {
        super(message);
    }

    public ProgramShouldBeTerminatedException(Throwable cause) {
        super(cause);
    }
}
