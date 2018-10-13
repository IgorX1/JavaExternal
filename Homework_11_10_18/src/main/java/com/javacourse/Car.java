package com.javacourse;

public class Car extends Vehicle implements Rideable{
    public Car(int price, int speed, int year) {
        this.price = price;
        this.speed = speed;
        this.yearManufactured = year;
    }
}
