package com.javacourse.serialization;

import com.javacourse.Car;
import com.javacourse.Vehicle;
import com.javacourse.serialization.VehicleSerializator;

import java.io.InvalidObjectException;

public class CarSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        Car car = null;
        car = (Car) objectDeserialization(fileName);
        return car;
    }
}
