package com.javacourse;

import java.io.InvalidObjectException;

public class ShipSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        Ship ship = null;
        ship = (Ship) objectDeserialization(fileName);
        return ship;
    }
}
