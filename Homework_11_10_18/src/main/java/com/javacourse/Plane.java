package com.javacourse;

public class Plane extends Vehicle implements Flyable {

    private int height;
    private int numberOfPassengers;

    public int getHeight() {
        return height;
    }

    public int getNumberOfPassangers() {
        return numberOfPassengers;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setNumberOfPassangers(int numberOfPassangers) {
        this.numberOfPassengers = numberOfPassangers;
    }
}
