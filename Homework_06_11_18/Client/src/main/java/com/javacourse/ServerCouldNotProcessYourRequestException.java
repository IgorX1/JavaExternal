package com.javacourse;

public class ServerCouldNotProcessYourRequestException extends RuntimeException {

    public ServerCouldNotProcessYourRequestException() {
    }

    public ServerCouldNotProcessYourRequestException(String message) {
        super(message);
    }

    public ServerCouldNotProcessYourRequestException(Throwable cause) {
        super(cause);
    }
}
