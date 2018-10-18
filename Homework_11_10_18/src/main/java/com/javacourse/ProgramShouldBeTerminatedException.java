package com.javacourse;

public class ProgramShouldBeTerminatedException extends RuntimeException {
    public ProgramShouldBeTerminatedException(String message) {
        super(message);
    }

    public ProgramShouldBeTerminatedException() {
        super();
    }
}
