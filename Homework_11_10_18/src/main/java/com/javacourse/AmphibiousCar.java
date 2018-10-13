package com.javacourse;

public class AmphibiousCar extends Vehicle implements Rideable, Swimable {
    public AmphibiousCar(int speed, int year,int price) {
        this.price = price;
        this.speed = speed;
        this.yearManufactured = year;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("AmphibiousCar{");
        sb.append("price=").append(price);
        sb.append(", speed=").append(speed);
        sb.append(", yearManufactured=").append(yearManufactured);
        sb.append('}');
        return sb.toString();
    }
}
