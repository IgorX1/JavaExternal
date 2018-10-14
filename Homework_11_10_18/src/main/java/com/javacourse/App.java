package com.javacourse;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class App
{
    static ArrayList<Vehicle> vehicles = new ArrayList<>();

    public static void main( String[] args )
    {
        fillVehicles();

        System.out.println("Query 1:");
        List<Vehicle> query = VehicleFinder.getPlanesWithHeightMoreThan5000YearAfter2000(vehicles);
        query.forEach(n-> System.out.println(n));

        System.out.println("Query 2:");
        query = VehicleFinder.getNotPlaneWithSpeedBetween200And500(vehicles);
        query.forEach(n-> System.out.println(n));

        FilteredResult query3 = VehicleFinder.getFilteredByWaysOfMOving(vehicles);
        System.out.println(query3);

        query = VehicleFinder.getWithMaximalSpeed(query3.flyable);
        System.out.println("Flyable with maximal speed:");
        query.forEach(n-> System.out.println(n));
        query = VehicleFinder.getWithMaximalSpeed(query3.rideable);
        System.out.println("Flyable with maximal rideable:");
        query.forEach(n-> System.out.println(n));
        query = VehicleFinder.getWithMaximalSpeed(query3.swimable);
        System.out.println("Swimable with maximal speed:");
        query.forEach(n-> System.out.println(n));
    }

    private static void fillVehicles(){
        vehicles.add(new Plane.PlaneBuilder(350, 2010, 1000)
                .numberOfPassengers(800)
                .height(12000)
                .build()
        );
        vehicles.add(new Plane.PlaneBuilder(250, 1999, 1000000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        vehicles.add(new Plane.PlaneBuilder(1100, 2005, 20000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        vehicles.add(new Ship.ShipBuilder(95, 2001, 300000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        vehicles.add(new Ship.ShipBuilder(80, 2001, 1000000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        vehicles.add(new Car(120, 2013, 150000));
        vehicles.add(new BatMobile(1200, 1985, 11111));
        vehicles.add(new AmphibiousCar(110, 2018, 170000));
        vehicles.add(new AmphibiousCar(250, 2018, 170000));
    }
}
