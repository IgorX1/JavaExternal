package com.javacourse;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.Objects;
import java.util.Random;

//Producer class
public class Ship implements Runnable{

    private int id;
    private int totalCapacity;
    private int currentCapacity;
    private final Harbor harbor;
    private final Dock dock;
    private static final Logger logger;

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
            swimToHarbor();
            serveCargo();
            leaveHarbor();
        }
    }

    private void swimToHarbor(){
        try {
            Thread.sleep(new Random().nextInt(1000));
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        System.out.printf("Ship %s takes dock %s\n", id, dock.getId());
    }

    private void leaveHarbor() {
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

    }


}
