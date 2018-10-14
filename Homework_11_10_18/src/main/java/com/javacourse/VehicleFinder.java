package com.javacourse;

import java.util.*;

public class VehicleFinder {

    public static List<Vehicle> getPlanesWithHeightMoreThan5000YearAfter2000(List<Vehicle> vehicles){
        final int YEAR_LIMIT = 2000;
        final int HEIGHT_LIMIT = 5000;

        List<Vehicle> res = new ArrayList();
        for(var v:vehicles){
            if(v instanceof Plane && v.getYearManufactured()>YEAR_LIMIT &&  ((Plane) v).getHeight()>HEIGHT_LIMIT){
                res.add(v);
            }
        }
        return res;
    }

    public static List<Vehicle> getNotPlaneWithSpeedBetween200And500(List<Vehicle> vehicles){
        final int SPEED_HIGH_LIMIT = 500;
        final int SPEED_LOW_LIMIT = 200;

        List<Vehicle> res = new ArrayList();
        for(var v:vehicles){
            if(!(v instanceof Plane) && v.getSpeed()>SPEED_LOW_LIMIT && v.getSpeed()<SPEED_HIGH_LIMIT){
                res.add(v);
            }
        }
        return res;
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
        int maximalSpeed = Collections
                .max(vehicles, (v1, v2)->{return v1.getSpeed()-v2.getSpeed();})
                .getSpeed();
        List<Vehicle> res = new ArrayList();
        vehicles.forEach(x->{
            if(x.getSpeed()==maximalSpeed) res.add(x);
        });
        return  res;
    }

    //TODO: implement it
    public static List<Vehicle> getWithMinPriceAndMaxSpeedYoungerThan5Years(List<Vehicle> vehicles){
        final int AGE_LIMIT = 5;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        int minPrice = Collections
                .min(vehicles, (Vehicle v1,Vehicle v2)->{return v1.getSpeed()-v2.getSpeed();})
                .getPrice();

        int maxSpeed = Collections
                .max(vehicles, new Comparator<Vehicle>() {
                    @Override
                    public int compare(Vehicle v1, Vehicle v2) {
                        return v1.getSpeed()-v2.getSpeed();
                    }
                }).getSpeed();

        List<Vehicle> res = new ArrayList();
        vehicles.forEach(x->{
            if(x.getSpeed()==maxSpeed
                && x.getPrice()==minPrice
                && currentYear - x.getYearManufactured() < AGE_LIMIT){
                res.add(x);
            }
        });
        return res;
    }


}
