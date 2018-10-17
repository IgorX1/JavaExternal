package com.javacourse;

import java.util.*;

public class VehicleModel {
    private ArrayList<Vehicle> resultingVehicles;

    public VehicleModel() {
        resultingVehicles = new ArrayList<>();//TODO:get rid of it
    }

    public void setResultingVehicles(ArrayList<Vehicle> resultingVehicles) {
        this.resultingVehicles = resultingVehicles;
    }

    public void clearResultingVehicles(){
        if(resultingVehicles.size()>0)
            resultingVehicles.clear();
    }


}
