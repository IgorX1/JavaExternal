package com.javacourse;

import java.util.ArrayList;

public class VehicleView {
    public void pringQueryResults(ArrayList<Vehicle> vehicles){
        vehicles.forEach(System.out::println);
    }

    public void pringQueryResults(ArrayList<Vehicle> vehicles, String message){
        System.out.println(message);
        vehicles.forEach(System.out::println);
    }

    public void printMessage(String message){
        System.out.println(message);
    }

    public void showMenu(){
        System.out.println("______VEHICLE FINDER MENU:______");
        System.out.println("1)Get planes by height and year");
        System.out.println("2)Get NOT planes by speed in segment");
        System.out.println("3)Get all vehicles with maximal speed");
        System.out.println("4)Get vehicles grouped by ways of moving");
        System.out.println("5)Get vehicles with minimal price maximal speed younger then X");
        System.out.println("6)Exit");
    }
}
