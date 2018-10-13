package com.javacourse;

import java.util.Objects;

public abstract class Vehicle implements Cloneable{

    protected int price;
    protected int speed;
    protected int yearManufactured;

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getYearManufactured() {
        return yearManufactured;
    }

    public void setYearManufactured(int yearManufactured) {
        this.yearManufactured = yearManufactured;
    }

    @Override
    public Vehicle clone(){
        try{
            return (Vehicle)super.clone();
        }catch (CloneNotSupportedException exc){
            throw new AssertionError();
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehicle vehicle = (Vehicle) o;
        return price == vehicle.price &&
                speed == vehicle.speed &&
                yearManufactured == vehicle.yearManufactured;
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, speed, yearManufactured);
    }
}
