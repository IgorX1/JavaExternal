package com.javacourse;

public class ClientConsoleView implements ClientView {
    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }
}
