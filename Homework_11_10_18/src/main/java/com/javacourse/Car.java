package com.javacourse;

public class Car extends Vehicle implements Rideable{
    public Car(int speed, int year,int price) {
        this.price = price;
        this.speed = speed;
        this.yearManufactured = year;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Car{");
        sb.append("price=").append(price);
        sb.append(", speed=").append(speed);
        sb.append(", yearManufactured=").append(yearManufactured);
        sb.append('}');
        return sb.toString();
    }
}
