package com.javacourse.serialization;

import com.javacourse.Vehicle;

import java.io.*;
import java.util.ArrayList;

public class ModelSerialization {

    public ArrayList<Vehicle> deserializeModel(String fileName) throws IOException {
        File file = new File(fileName);
        ObjectInputStream ois = null;
        try {
            FileInputStream vehiclesInputStream = new FileInputStream(file);
            ois = new ObjectInputStream(vehiclesInputStream);
            return (ArrayList<Vehicle>) ois.readObject();
        } catch (ClassNotFoundException e) {
            throw new IOException();
        } finally {
                if(ois!=null){
                    ois.close();
                }
        }
    }
}
