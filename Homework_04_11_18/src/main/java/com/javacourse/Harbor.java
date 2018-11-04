package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Harbor {
    private static final int DOCK_NUMBER;
    private static final int TOTAL_CAPACITY;
    private final Semaphore semaphore;
    private static final Logger logger;
    int currentCapacity;
    private String name;
    private final BlockingQueue<Dock> docks;

    static {
        DOCK_NUMBER = 2;
        TOTAL_CAPACITY = 15;
        logger = Logger.getLogger(Ship.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public Harbor(String name) {
        this.name = name;
        this.semaphore = new Semaphore(DOCK_NUMBER, true);
        this.currentCapacity  = TOTAL_CAPACITY/2 + 1;
        this.docks = new LinkedBlockingQueue<>(2);
        initializeDockList();
        System.out.printf("Harbor %s is ready to serve ships\n", name);
        System.out.printf("Current harbor capacity:%s\n", currentCapacity);
        System.out.printf("Total harbor capacity:%s\n", TOTAL_CAPACITY);
    }

    private void initializeDockList() {
        for(int i=0; i<DOCK_NUMBER; ++i){
            docks.add(new Dock(i));
        }
    }

    public Dock getResource(int maxWaitMilliseconds) throws ResourceException {
        try {
            if(semaphore.tryAcquire(maxWaitMilliseconds, TimeUnit.MILLISECONDS)){
                return docks.poll();
            }
        } catch (InterruptedException e) {
            logger.info(e.getMessage());
        }
        throw new ResourceException("Waiting time is exceeded");
    }

    public void releaseResource(Dock dock){
        docks.add(dock);
        semaphore.release();
    }

    public String getName() {
        return name;
    }

    public int getCurrentCapacity() {
        return currentCapacity;
    }

    public static int getTotalCapacity() {
        return TOTAL_CAPACITY;
    }
}
