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

    public boolean clearVehicles(){
        if(vehicles==null)
            return false;

        if(vehicles.size()>0){
            vehicles.clear();
            return true;
        }
        return  false;
    }

    public boolean add(Vehicle v){
        if(v!=null){
            vehicles.add(v);
            return true;
        }
        return false;
    }



}
