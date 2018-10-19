package com.javacourse;

import java.util.ArrayList;
import java.util.List;

public class VehicleView {
    public static final String WRONG_INPUT_INT_DATA = "Wrong input! Repeat please! ";
    public static final String RPT_INPUT = "Repeat your input";

    public void pringQueryResults(List<Vehicle> vehicles){
        vehicles.forEach(System.out::println);
    }

    public void pringQueryResults(List<Vehicle> vehicles, String message){
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
        System.out.println("4)Get vehicles with minimal price maximal speed younger then X");
    }

}
