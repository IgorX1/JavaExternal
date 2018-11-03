package com.javacourse;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

//Consumer class
public class Harbor{
    private static final int DOCK_NUMBER = 2;
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

    public synchronized Dock getDock() throws NoAvailableDocksException{
        for(Dock d : docks)
            if(d.isFree())
                return d;
        throw new NoAvailableDocksException("No free docks");
    }
}
