package com.javacourse;

import java.util.*;

public class VehicleController {

    //The list of vehicles we're working on
    private ArrayList<Vehicle> vehicleList;
    private VehicleView view;
    private VehicleModel model;

    public VehicleController(VehicleView view,VehicleModel model) {
        this.view = view;
        this.model = model;
        vehicleList = new ArrayList<>();
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);
        while(true){
            view.showMenu();
            model.setResultingVehicles();
        }
    }


}
