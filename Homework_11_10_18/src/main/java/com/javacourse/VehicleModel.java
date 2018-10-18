package com.javacourse;

import java.util.*;

public class VehicleModel {
    private List<Vehicle> vehicles;

    public VehicleModel() {
        vehicles = new ArrayList<>();
    }

    public void setVehicles(List<Vehicle> resultingVehicles) {
        this.vehicles = resultingVehicles;
    }

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public void clearVehicles(){
        if(vehicles.size()>0)
            vehicles.clear();
    }

    public void add(Vehicle v){
        vehicles.add(v);
    }



}
