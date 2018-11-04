package com.javacourse;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

//Consumer class
public class Harbor{
    private static final int DOCK_NUMBER = 2;
    private static final int TOTAL_CAPACITY = 15;
    private String name;
    private List<Dock> docks = new ArrayList<>();
    int currentCapacity = TOTAL_CAPACITY/2;

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
    //TODO: implement efficient dock allocation
    public Dock getDockToSwimTo(){
        return docks.get(new Random().nextInt(DOCK_NUMBER));
    }
}
