package com.javacourse;

import javax.print.Doc;
import java.util.ArrayList;
import java.util.List;

//Consumer class
public class Harbor implements Runnable {
    private static final int DOCK_NUMBER = 4;
    private List<Dock> docks = new ArrayList<>();

    public Harbor(){
        initializeDockList();
    }

    private void initializeDockList(){
        for(int i=0; i<DOCK_NUMBER; ++i){
            docks.add(new Dock(i));
        }
    }

    public List<Dock> getDocks() {
        return docks;
    }

    @Override
    public void run() {

    }
}
