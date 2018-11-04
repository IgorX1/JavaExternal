package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Ship implements Runnable{
    private int id;
    private int totalCapacity;
    private int currentCapacity;
    private final Harbor harbor;
    private Lock lock = new ReentrantLock();
    private Condition isAvailable = lock.newCondition();
    private static final Logger logger;
    private static final int MAX_TIME_TO_WAIT = 1000;

    static {
        logger = Logger.getLogger(Ship.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    private Ship(ShipBuilder builder) {
        this.id = builder.id;
        this.harbor = builder.harbor;
        this.totalCapacity = builder.totalCapacity;
        this.currentCapacity = builder.currentCapacity;
        System.out.printf("Ship #%d is willing to enter the harbor %s\n", id, harbor.getName());
    }

    /**
     * Making use of Builder pattern
     */
    public static class ShipBuilder{
        private int id;
        private int totalCapacity;
        private int currentCapacity;
        private Harbor harbor;

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
        Dock dockToSwim = null;
        try {
            dockToSwim = harbor.getResource(MAX_TIME_TO_WAIT);
            System.out.printf("Ship %s takes dock %s\n", id, dockToSwim.getId());
            serveCargo();
        } catch (ResourceException e) {
            logger.info(e.getMessage());
            System.out.printf("Ship %s can't wait anymore. This harbor is not hospitable...\n", id);
        } finally {
             if(dockToSwim!=null){
                 System.out.printf("Ship %s left dock %s\n", id, dockToSwim.getId());
                 System.out.printf("Dock %s is free\n", dockToSwim.getId());
                 harbor.releaseResource(dockToSwim);
             }
        }
    }

    private void serveCargo() {
        if(currentCapacity==0){
            loadShip();
        }else {
            unloadShip();
            loadShip();
        }
    }

    private void loadShip() {
        lock.lock();
        try {
            boolean isLoadingSuccessful = true;
            while (harbor.getCurrentCapacity()==0){
                try {
                    isAvailable.await(MAX_TIME_TO_WAIT, TimeUnit.MILLISECONDS);
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
            isAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

    private void unloadShip() {
        lock.lock();
        try {
            boolean isUnloadingSuccessful = true;
            while(harbor.currentCapacity==harbor.getTotalCapacity()) {
                try {
                    isAvailable.await(MAX_TIME_TO_WAIT, TimeUnit.MILLISECONDS);
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
            isAvailable.signal();
        } finally {
            lock.unlock();
        }
    }

}
