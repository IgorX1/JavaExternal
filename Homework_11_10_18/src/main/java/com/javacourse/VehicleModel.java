package com.javacourse;

import java.util.*;

public class VehicleModel {
    private List<Vehicle> resultingVehicles;

    public VehicleModel() {
        resultingVehicles = new ArrayList<>();//TODO:get rid of it
    }

    public void setResultingVehicles(List<Vehicle> resultingVehicles) {
        this.resultingVehicles = resultingVehicles;
    }

    public List<Vehicle> getResultingVehicles() {
        return resultingVehicles;
    }

    public void clearResultingVehicles(){
        if(resultingVehicles.size()>0)
            resultingVehicles.clear();
    }



}
