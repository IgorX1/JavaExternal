package com.javacourse;

import java.util.Objects;

//Producer class
public class Ship implements Runnable{

    private int id;
    private int totalCapacity;
    private int currentCapacity;

    private Ship(ShipBuilder builder) {
        this.id = builder.id;
        this.totalCapacity = builder.totalCapacity;
        this.currentCapacity = builder.currentCapacity;
    }

    public static class ShipBuilder{
        private int id;
        private int totalCapacity;
        private int currentCapacity;

        public ShipBuilder(int id) {
            this.id = id;
        }

        public ShipBuilder totalCapacity(int totalCapacity){
            this.totalCapacity = totalCapacity;
            return this;
        }

        public ShipBuilder currentCapacity(int currentCapacity){
            this.currentCapacity = currentCapacity;
            return this;
        }

        public Ship build(){
            return new Ship(this);
        }
    }

    @Override
    public void run() {
        System.out.printf("Ship #%d is going to enter the port", id);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Ship[");
        sb.append("id=").append(id);
        sb.append(", currentCapacity=").append(currentCapacity);
        sb.append(']');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ship)) return false;
        Ship ship = (Ship) o;
        return id == ship.id &&
                totalCapacity == ship.totalCapacity &&
                currentCapacity == ship.currentCapacity;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, totalCapacity, currentCapacity);
    }
}
