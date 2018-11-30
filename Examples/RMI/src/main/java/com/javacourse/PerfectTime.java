package com.javacourse;

import java.rmi.*;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

/**
 * Server
 */
public class PerfectTime implements PerfectTimeI {

    public PerfectTime() throws RemoteException {

    }

    @Override
    public long getPerfectTime() throws RemoteException {
        return System.currentTimeMillis();
    }

    public static void main(String[] args) throws Exception {
        try {
            PerfectTime perfectTime = new PerfectTime();
            PerfectTimeI stub = (PerfectTimeI) UnicastRemoteObject.exportObject(perfectTime, 0);
            Registry registry = LocateRegistry.createRegistry(1888);
            registry.bind("Hello", stub);
            System.err.println("Server ready");
        } catch (Exception e) {
            System.err.println("Server exception: " + e.toString());
            e.printStackTrace();
        }
    }

}
