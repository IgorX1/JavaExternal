package com.javacourse;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Hello world!
 *
 */
public class App 
{
    /**
     * Client
     * @param args
     * @throws RemoteException
     * @throws NotBoundException
     * @throws MalformedURLException
     */
    public static void main( String[] args ) throws RemoteException, NotBoundException, MalformedURLException {
        try {
            Registry registry = LocateRegistry.getRegistry(1888);
            PerfectTimeI stub = (PerfectTimeI) registry.lookup("Hello");
            long response = stub.getPerfectTime();
            System.out.println("response: " + response);
        } catch (Exception e) {
            System.err.println("Client exception: " + e.toString());
            e.printStackTrace();
        }
    }
}
