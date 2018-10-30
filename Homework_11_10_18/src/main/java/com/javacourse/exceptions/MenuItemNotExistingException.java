package com.javacourse.exceptions;

public class MenuItemNotExistingException extends RuntimeException {
    public MenuItemNotExistingException(String message) {
        super(message);
    }
}
