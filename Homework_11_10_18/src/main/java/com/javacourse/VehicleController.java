package com.javacourse;

import java.util.*;

public class VehicleController {

    //The list of vehicles we're working on
    private ArrayList<Vehicle> vehicleList;

    private VehicleView view;
    private VehicleModel model;

    public VehicleController(VehicleView view,VehicleModel model) {
        this.view = view;
        this.model = model;
        vehicleList = new ArrayList<>();
        fillVehicles();
    }

    private void fillVehicles(){
        vehicleList.add(new Plane.PlaneBuilder(350, 2010, 1000)
                .numberOfPassengers(800)
                .height(12000)
                .build()
        );
        vehicleList.add(new Plane.PlaneBuilder(250, 1999, 1000000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        vehicleList.add(new Plane.PlaneBuilder(1100, 2005, 20000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        vehicleList.add(new Ship.ShipBuilder(95, 2001, 300000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        vehicleList.add(new Ship.ShipBuilder(80, 2001, 1000000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        vehicleList.add(new Car(120, 2013, 150000));
        vehicleList.add(new BatMobile(1200, 1985, 11111));
        vehicleList.add(new AmphibiousCar(110, 2018, 170000));
        vehicleList.add(new AmphibiousCar(250, 2018, 170000));
    }


    public void processUser(){
        Scanner sc = new Scanner(System.in);
        while(true){
            model.clearResultingVehicles();
            if(stopIfNecessary(sc))
                break;
            view.pringQueryResults(model.getResultingVehicles());
        }
    }

    private boolean stopIfNecessary(Scanner sc){
        try
        {
            model.setResultingVehicles(getResultOnUsersChoice(getUserChoice(sc), sc));
        }catch (ProgramShouldBeTerminatedException exc){
            return true;
        }
        catch ()

        return false;
    }

    private int getUserChoice(Scanner sc){
        view.showMenu();
        view.printMessage("Choose the menu item:");
        while( ! sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA);
            view.printMessage("Choose the menu item");
            sc.next();
        }
        return sc.nextInt();
    }
    private List<Vehicle> getResultOnUsersChoice(int choice, Scanner sc){
        switch (choice){
            case 1:
                int height = getParamFromConsole("Height value:", sc);
                int year = getParamFromConsole("Year value:", sc);
                return VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(vehicleList, year, height);
            case 2:
                int minspeed = getParamFromConsole("Min speed", sc);
                int maxspeed = getParamFromConsole("Max speed", sc);
                return  VehicleFinder.getNotPlaneWithSpeedBetweenXAndY(vehicleList, minspeed, maxspeed);
            case 3:
                return VehicleFinder.getWithMaximalSpeed(vehicleList);
            case 4:
                int ageLimit = getParamFromConsole("Age limit", sc);
                return  VehicleFinder.getWithMinPriceAndMaxSpeedYoungerThanXYears(vehicleList, ageLimit);
            case 5:
                throw new ProgramShouldBeTerminatedException();
            default:
                throw new MenuItemNotExistingExcpetion("Such menu item does not exist");
        }
    }

    private int getParamFromConsole(String msg, Scanner sc){
        view.printMessage(msg);
        int param = 0;
        while( ! sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA);
            view.printMessage(msg);
            sc.next();
        }
        return sc.nextInt();

    }


}
