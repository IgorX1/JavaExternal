package com.javacourse;

import java.util.Objects;
import java.util.Random;

//Producer class
public class Ship implements Runnable{

    private int id;
    private int totalCapacity;
    private int currentCapacity;
    Harbor harbor;
    Dock dock;

    private Ship(ShipBuilder builder) {
        this.id = builder.id;
        this.totalCapacity = builder.totalCapacity;
        this.currentCapacity = builder.currentCapacity;
        this.harbor = builder.harbor;
        this.dock = builder.dock;
    }

    public static class ShipBuilder{
        private int id;
        private int totalCapacity;
        private int currentCapacity;
        private Harbor harbor;
        private Dock dock;

        public ShipBuilder(int id, Harbor harbor) {
            this.id = id;
            this.harbor = harbor;
        }

        public ShipBuilder totalCapacity(int totalCapacity){
            this.totalCapacity = totalCapacity;
            return this;
        }

        public ShipBuilder currentCapacity(int currentCapacity){
            this.currentCapacity = currentCapacity;
            return this;
        }

        public Ship build(){
            return new Ship(this);
        }
    }

    @Override
    public void run() {
        System.out.printf("Ship #%d is willing to enter the harbor %s\n", id, harbor.getName());
        swimToHarbor();
        synchronized (dock){

        }
    }

    public void swimToHarbor(){
        getDock();
        try {
            Thread.sleep(new Random(100).nextInt(1000));
        } catch (InterruptedException e) {
            //TODO:log it
        }
        System.out.printf("Ship %s takes dock %s\n", id, dock.getId());
    }

    private synchronized void getDock() {
        do{
            try{
                dock = harbor.getDock();
                dock.setFree(false);
            }catch (NoAvailableDocksException e){
                try {
                    System.out.printf("No free docks. Ship %s is waiting...\n", id);
                    wait();
                } catch (InterruptedException e1) {
                    System.err.printf(e1.getMessage());
                    //TODO:log it
                }
            }

        }while (!isDockSet());
    }

    private boolean isDockSet(){
        return dock!=null;
    }


}
