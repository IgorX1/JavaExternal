package com.javacourse;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Harbor{
    private static final int DOCK_NUMBER = 2;
    private static final int TOTAL_CAPACITY = 15;
    int currentCapacity = TOTAL_CAPACITY/2;//supposing harbor was not completely empty
    private String name;
    private List<Dock> docks = new ArrayList<>();

    public Harbor(String name){
        this.name = name;
        initializeDockList();
        System.out.printf("Harbor %s is ready to serve ships\n", name);
    }

    private void initializeDockList(){
        for(int i=0; i<DOCK_NUMBER; ++i){
            docks.add(new Dock(i));
        }
    }

    public String getName() {
        return name;
    }

    public int getTotalCapacity() {
        return TOTAL_CAPACITY;
    }


    public int getCurrentCapacity() {
        return currentCapacity;
    }

    /*Supposing docks are predefined for the destination*/
    public Dock getDockToSwimTo(){
        return docks.get(new Random().nextInt(DOCK_NUMBER));
    }
}
