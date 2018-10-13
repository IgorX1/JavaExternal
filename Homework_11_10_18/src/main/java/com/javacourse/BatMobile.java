package com.javacourse;

public class BatMobile extends Vehicle implements Swimable, Rideable, Flyable{
    public BatMobile(int price, int speed, int year) {
        this.price = price;
        this.speed = speed;
        this.yearManufactured = year;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BatMobile{");
        sb.append("price=").append(price);
        sb.append(", speed=").append(speed);
        sb.append(", yearManufactured=").append(yearManufactured);
        sb.append('}');
        return sb.toString();
    }
}
