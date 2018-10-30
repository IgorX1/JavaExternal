package com.javacourse;

import java.io.InvalidObjectException;

public class CarSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        Car car = null;
        car = (Car) objectDeserialization(fileName);
        return car;
    }
}
