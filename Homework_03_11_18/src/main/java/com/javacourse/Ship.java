package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import java.util.Random;

public class Ship implements Runnable{

    private int id;
    private int totalCapacity;
    private int currentCapacity;
    private final Harbor harbor;
    private final Dock dock;
    private static final Logger logger;
    private static final int MAX_TIME_TO_WAIT = 1000;

    static {
        logger = Logger.getLogger(Ship.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

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
            this.dock = harbor.getDockToSwimTo();
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
        synchronized (dock){
            swimToDock();
            serveCargo();
            leaveDock();
        }
    }

    private void swimToDock(){
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        System.out.printf("Ship %s takes dock %s\n", id, dock.getId());
    }

    private void leaveDock() {
            try {
                Thread.sleep(new Random().nextInt(1000));
                System.out.printf("Ship %s left dock %s\n", id, dock.getId());
            } catch (InterruptedException e) {
                logger.error(e.getMessage());
            } finally {
                System.out.printf("Dock %s is free\n", dock.getId());
            }
    }

    private void serveCargo(){
        if(currentCapacity==0){
            loadShip();
        }else {
            unloadShip();
            loadShip();
        }
    }

    private void unloadShip() {
        synchronized (harbor){
            boolean isUnloadingSuccessful = true;
            while(harbor.currentCapacity==harbor.getTotalCapacity()) {
                try {
                    harbor.wait(MAX_TIME_TO_WAIT);
                    isUnloadingSuccessful = false;
                    System.out.printf("Ship %s can't wait anymore. It will swim home without unloading...\n", id);
                    break;
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
            while (harbor.getCurrentCapacity()<harbor.getTotalCapacity()
                    && this.currentCapacity>0){
                currentCapacity--;
                harbor.currentCapacity++;
            }
            if(isUnloadingSuccessful)
                System.out.printf("Ship %s unloaded cargo. Current ship capacity:%s\n", id, currentCapacity);
            System.out.printf("Current harbor capacity: %s\n", harbor.currentCapacity);
            harbor.notify();
        }

    }

    private void loadShip() {
        synchronized (harbor){
            boolean isLoadingSuccessful = true;
            while (harbor.getCurrentCapacity()==0){
                try {
                    harbor.wait(MAX_TIME_TO_WAIT);
                    isLoadingSuccessful = false;
                    System.out.printf("Ship %s can't wait anymore. It will swim home without loading...\n", id);
                    break;
                } catch (InterruptedException e) {
                    logger.error(e.getMessage());
                }
            }
            while(harbor.getCurrentCapacity()>0
                    && this.currentCapacity<this.totalCapacity){
                currentCapacity++;
                harbor.currentCapacity--;
            }
            if(isLoadingSuccessful)
                System.out.printf("Ship %s loaded cargo. Current ship capacity:%s\n", id, currentCapacity);
            System.out.printf("Current harbor capacity: %s\n", harbor.currentCapacity);
            harbor.notify();
        }
    }


}
