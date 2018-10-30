package com.javacourse;

import java.io.InvalidObjectException;

public class BatMobileSerializator extends VehicleSerializator {
    @Override
    Vehicle deserialization(String fileName) throws InvalidObjectException {
        BatMobile batMobile = null;
        batMobile = (BatMobile) objectDeserialization(fileName);
        return batMobile;
    }
}
