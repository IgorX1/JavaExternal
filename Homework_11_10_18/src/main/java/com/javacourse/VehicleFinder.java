package com.javacourse;

import java.util.*;

public class VehicleFinder {

    public static List<Vehicle> getPlanesWithHeightMoreThan5000YearAfter2000(List<Vehicle> vehicles){
        List<Vehicle> res = new ArrayList();
        for(var v:vehicles){
            if(isPlaneWithHeightMoreThan5000YearAfter2000(v)){
                res.add(v);
            }
        }
        return res;
    }

    private static boolean isPlaneWithHeightMoreThan5000YearAfter2000(Vehicle v){
        final int YEAR_LIMIT = 2000;
        final int HEIGHT_LIMIT = 5000;

        return (v instanceof Plane
                && v.getYearManufactured()>YEAR_LIMIT
                && ((Plane) v).getHeight()>HEIGHT_LIMIT);

    }

    public static List<Vehicle> getNotPlaneWithSpeedBetween200And500(List<Vehicle> vehicles){
        List<Vehicle> filteredVehicles = new ArrayList();
        for(var v:vehicles){
            if(isNotPlaneWithSpeedBetween200And500(v)){
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    private static boolean isNotPlaneWithSpeedBetween200And500(Vehicle v){
        final int SPEED_HIGH_LIMIT = 500;
        final int SPEED_LOW_LIMIT = 200;
        return !(v instanceof Plane)
                && v.getSpeed()>SPEED_LOW_LIMIT
                && v.getSpeed()<SPEED_HIGH_LIMIT;
    }

    public static FilteredResult getFilteredByWaysOfMOving(List<Vehicle> vehicles){
        FilteredResult filteredResult = new FilteredResult();
        for(var v:vehicles){
            if(v instanceof Rideable)
                filteredResult.rideable.add(v);
            if(v instanceof Swimable)
                filteredResult.swimable.add(v);
            if(v instanceof Flyable)
                filteredResult.flyable.add(v);
        }
        return filteredResult;
    }

    public static List<Vehicle> getWithMaximalSpeed(List<Vehicle> vehicles){
        int maximalSpeed = vehicles
                .stream()
                .max(Comparator.comparing(Vehicle::getSpeed))
                .get()
                .getSpeed();
        List<Vehicle> res = new ArrayList<>();
        vehicles.forEach(x->{
            if(x.getSpeed()==maximalSpeed) res.add(x);
        });
        return  res;
    }

    public static List<Vehicle> getWithMinPriceAndMaxSpeedYoungerThan5Years(List<Vehicle> vehicles){
        int minPrice = vehicles
                .stream()
                .min(Comparator.comparing(Vehicle::getPrice))
                .get()
                .getPrice();

        int maxSpeed = vehicles
                .stream()
                .max(Comparator.comparing(Vehicle::getSpeed))
                .get()
                .getSpeed();

        List<Vehicle> res = new ArrayList<>();
        vehicles.forEach(x->{
            if(hasMaxSpeedMinPriceAndYoungerThenWanted(x, maxSpeed, minPrice)){
                res.add(x);
            }
        });
        return res;
    }

    private static boolean hasMaxSpeedMinPriceAndYoungerThenWanted(Vehicle vehicle,
                                                                   int maxSpeed,
                                                                   int minPrice){
        final int AGE_LIMIT = 5;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return (vehicle.getSpeed()== maxSpeed
                && vehicle.getPrice()== minPrice
                && currentYear - vehicle.getYearManufactured() < AGE_LIMIT);
    }

}
