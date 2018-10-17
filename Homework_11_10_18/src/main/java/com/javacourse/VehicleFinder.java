package com.javacourse;
import java.util.*;

public class VehicleFinder {
    public static List<Vehicle> getPlanesWithHeightMoreThanXYearAfterY(List<Vehicle> vehicles,
                                                                       int year,
                                                                       int height){
        List<Vehicle> res = new ArrayList<>();
        for(var v:vehicles){
            if(isPlaneWithHeightMoreThanXYearAfterY(v, year, height)){
                res.add(v);
            }
        }
        return res;
    }

    private static boolean isPlaneWithHeightMoreThanXYearAfterY(Vehicle v, int year, int height){
        return (v instanceof Plane
                && v.getYearManufactured()>year
                && ((Plane) v).getHeight()>height);

    }

    public static List<Vehicle> getNotPlaneWithSpeedBetweenXAndY(List<Vehicle> vehicles,
                                                                 int low,
                                                                 int high){
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for(var v:vehicles){
            if(isNotPlaneWithSpeedBetweenXAndY(v, low, high)){
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    private static boolean isNotPlaneWithSpeedBetweenXAndY(Vehicle v, int low, int high){
        return !(v instanceof Plane)
                && v.getSpeed()>low
                && v.getSpeed()<high;
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

    public static List<Vehicle> getWithMinPriceAndMaxSpeedYoungerThanXYears(List<Vehicle> vehicles, int years){
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
            if(hasMaxSpeedMinPriceAndYoungerThenWanted(x, maxSpeed, minPrice, years)){
                res.add(x);
            }
        });
        return res;
    }

    private static boolean hasMaxSpeedMinPriceAndYoungerThenWanted(Vehicle vehicle,
                                                                   int maxSpeed,
                                                                   int minPrice,
                                                                   int years){
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return (vehicle.getSpeed()== maxSpeed
                && vehicle.getPrice()== minPrice
                && currentYear - vehicle.getYearManufactured() < years);
    }
}
