package com.javacourse;

import java.util.Objects;

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

    public static class ShipBuilder{
        private int speed;
        private int yearManufactured;
        private int price;
        private int numberOfPassengers;
        private int port;

        public ShipBuilder(int speed, int yearManufactured, int price) {
            this.speed = speed;
            this.yearManufactured = yearManufactured;
            this.price = price;
        }

        public ShipBuilder numberOfPassengers(int passengers){
            this.numberOfPassengers = passengers;
            return this;
        }

        public ShipBuilder port(int port){
            this.port = port;
            return this;
        }

        public Ship build(){
            return new Ship(this);
        }
    }

    private Ship(ShipBuilder builder){
        this.numberOfPassengers = builder.numberOfPassengers;
        this.port = builder.port;
        this.price = builder.price;
        this.speed = builder.speed;
        this.yearManufactured = builder.yearManufactured;
    }

    @Override
    public String toString() {
        return "Ship{" +
                "numberOfPassengers=" + numberOfPassengers +
                ", port=" + port +
                ", price=" + price +
                ", speed=" + speed +
                ", yearManufactured=" + yearManufactured +
                "} ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        if (!super.equals(o)) return false;//allows us not to check these fields below
        Ship ship = (Ship) o;
        return numberOfPassengers == ship.numberOfPassengers &&
                port == ship.port;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), numberOfPassengers, port);
    }
}
