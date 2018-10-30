package com.javacourse.serialization;

import com.javacourse.BatMobile;
import com.javacourse.Vehicle;
import com.javacourse.serialization.VehicleSerializator;

import java.io.InvalidObjectException;

public class BatMobileSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        BatMobile batMobile = null;
        batMobile = (BatMobile) objectDeserialization(fileName);
        return batMobile;
    }
}
