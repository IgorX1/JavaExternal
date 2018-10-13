package com.javacourse;

public class Ship extends Vehicle implements Swimable {
    private int numberOfPassengers;
    private int port;

    public int getNumberOfPassengers() {
        return numberOfPassengers;
    }

    public int getPort() {
        return port;
    }

    public void setNumberOfPassengers(int numberOfPassengers) {
        this.numberOfPassengers = numberOfPassengers;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
