package com.javacourse;

import java.util.Objects;

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

    public static class PlaneBuilder{
        private int speed;
        private int yearManufactured;
        private int price;
        private int numberOfPassengers;
        private int height;

        public PlaneBuilder(int speed, int yearManufactured, int price) {
            this.speed = speed;
            this.yearManufactured = yearManufactured;
            this.price = price;
        }

        public PlaneBuilder numberOfPassengers(int passengers){
            this.numberOfPassengers = passengers;
            return this;
        }

        public PlaneBuilder height(int height){
            this.height = height;
            return this;
        }

        public Plane build(){
            return new Plane(this);
        }
    }

    private Plane(PlaneBuilder builder){
        this.numberOfPassengers = builder.numberOfPassengers;
        this.height = builder.height;
        this.price = builder.price;
        this.speed = builder.speed;
        this.yearManufactured = builder.yearManufactured;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Plane{");
        sb.append("height=").append(height);
        sb.append(", numberOfPassengers=").append(numberOfPassengers);
        sb.append(", price=").append(price);
        sb.append(", speed=").append(speed);
        sb.append(", yearManufactured=").append(yearManufactured);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Plane)) return false;
        if (!super.equals(o)) return false;
        Plane plane = (Plane) o;
        return height == plane.height &&
                numberOfPassengers == plane.numberOfPassengers;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), height, numberOfPassengers);
    }
}
