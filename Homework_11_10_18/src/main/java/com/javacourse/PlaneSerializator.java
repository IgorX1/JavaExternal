package com.javacourse;

import java.io.*;

public class PlaneSerializator extends VehicleSerializator {

    @Override
    public Vehicle deserialization(String fileName) throws InvalidObjectException {
        Plane plane = null;
        plane = (Plane) objectDeserialization(fileName);
        return plane;
    }
}
