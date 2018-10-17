package com.javacourse;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Comparator;
import java.util.List;

public class VehicleModel {
    private ArrayList<Vehicle> vehicles;
    private ArrayList<Vehicle> resultingVehicles;

    public VehicleModel() {
        vehicles =  new ArrayList<>();
        resultingVehicles = new ArrayList<>();
        fillVehicles();
    }

    private void fillVehicles(){
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

    public void findResultingVehicles() {

    }

    public void addVehicle(Vehicle v){
        this.vehicles.add(v);
    }

    public void clearResultingVehicles(){
        if(vehicles.size()>0)
            vehicles.clear();
    }

    private List<Vehicle> getPlanesWithHeightMoreThan5000YearAfter2000(List<Vehicle> vehicles){
        List<Vehicle> res = new ArrayList<>();
        for(var v:vehicles){
            if(isPlaneWithHeightMoreThan5000YearAfter2000(v)){
                res.add(v);
            }
        }
        return res;
    }

    private boolean isPlaneWithHeightMoreThan5000YearAfter2000(Vehicle v){
        final int YEAR_LIMIT = 2000;
        final int HEIGHT_LIMIT = 5000;

        return (v instanceof Plane
                && v.getYearManufactured()>YEAR_LIMIT
                && ((Plane) v).getHeight()>HEIGHT_LIMIT);

    }

    private List<Vehicle> getNotPlaneWithSpeedBetween200And500(List<Vehicle> vehicles){
        List<Vehicle> filteredVehicles = new ArrayList<>();
        for(var v:vehicles){
            if(isNotPlaneWithSpeedBetween200And500(v)){
                filteredVehicles.add(v);
            }
        }
        return filteredVehicles;
    }

    private boolean isNotPlaneWithSpeedBetween200And500(Vehicle v){
        final int SPEED_HIGH_LIMIT = 500;
        final int SPEED_LOW_LIMIT = 200;
        return !(v instanceof Plane)
                && v.getSpeed()>SPEED_LOW_LIMIT
                && v.getSpeed()<SPEED_HIGH_LIMIT;
    }

    private FilteredResult getFilteredByWaysOfMOving(List<Vehicle> vehicles){
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

    private List<Vehicle> getWithMaximalSpeed(List<Vehicle> vehicles){
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

    private List<Vehicle> getWithMinPriceAndMaxSpeedYoungerThan5Years(List<Vehicle> vehicles){
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

    private boolean hasMaxSpeedMinPriceAndYoungerThenWanted(Vehicle vehicle,
                                                            int maxSpeed,
                                                            int minPrice){
        final int AGE_LIMIT = 5;
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);

        return (vehicle.getSpeed()== maxSpeed
                && vehicle.getPrice()== minPrice
                && currentYear - vehicle.getYearManufactured() < AGE_LIMIT);
    }

}
