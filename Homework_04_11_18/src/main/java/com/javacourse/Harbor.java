package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class Harbor {
    private static final int DOCK_NUMBER;
    private static final int TOTAL_CAPACITY;
    private final Semaphore semaphore;
    private static final Logger logger;
    int currentCapacity;
    private String name;
    private final Queue<Dock> docks;

    static {
        DOCK_NUMBER = 2;
        TOTAL_CAPACITY = 15;
        logger = Logger.getLogger(Ship.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public Harbor(String name) {
        this.name = name;
        this.semaphore = new Semaphore(DOCK_NUMBER, true);
        this.currentCapacity  = TOTAL_CAPACITY/2;
        this.docks = new LinkedList<>();
        initializeDockList();
        System.out.printf("Harbor %s is ready to serve ships\n", name);
    }

    private void initializeDockList() {
        for(int i=0; i<DOCK_NUMBER; ++i){
            docks.add(new Dock(i));
        }
    }

    public Dock getResource(int maxWaitMilliseconds) throws ResourceException {
        try {
            if(semaphore.tryAcquire(maxWaitMilliseconds, TimeUnit.MILLISECONDS)){
                Dock res = docks.poll();
                return res;
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
}
