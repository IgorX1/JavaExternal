package com.javacourse.serialization;

import com.javacourse.AmphibiousCar;
import com.javacourse.Vehicle;
import com.javacourse.serialization.VehicleSerializator;

import java.io.InvalidObjectException;

public class AmphibiousCarSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        AmphibiousCar amphibiousCar = null;
        amphibiousCar = (AmphibiousCar) objectDeserialization(fileName);
        return amphibiousCar;
    }
}
