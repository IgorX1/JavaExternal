package com.javacourse;

import java.io.InvalidObjectException;

public class AmphibiousCarSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        AmphibiousCar amphibiousCar = null;
        amphibiousCar = (AmphibiousCar) objectDeserialization(fileName);
        return amphibiousCar;
    }
}
