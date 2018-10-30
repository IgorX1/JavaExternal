package com.javacourse.serialization;

import com.javacourse.Plane;
import com.javacourse.Vehicle;
import com.javacourse.serialization.VehicleSerializator;

import java.io.*;

public class PlaneSerializator extends VehicleSerializator {

    @Override
    public Vehicle deserialization(String fileName) throws InvalidObjectException {
        Plane plane = null;
        plane = (Plane) objectDeserialization(fileName);
        return plane;
    }
}
