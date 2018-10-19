package com.javacourse;

import java.util.*;

public class VehicleController {

    private VehicleView view;
    private VehicleModel model;

    public VehicleController(VehicleView view,VehicleModel model) {
        this.view = view;
        this.model = model;
        fillVehicles();
    }

    private void fillVehicles(){
        model.add(new Plane.PlaneBuilder(350, 2010, 1000)
                .numberOfPassengers(800)
                .height(12000)
                .build()
        );
        model.add(new Plane.PlaneBuilder(250, 1999, 1000000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        model.add(new Plane.PlaneBuilder(1100, 2005, 20000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        model.add(new Ship.ShipBuilder(95, 2001, 300000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        model.add(new Ship.ShipBuilder(80, 2001, 1000000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        model.add(new Car(120, 2013, 150000));
        model.add(new BatMobile(1200, 1985, 11111));
        model.add(new AmphibiousCar(110, 2018, 170000));
        model.add(new AmphibiousCar(250, 2018, 170000));
    }

    public void processUser(){
        Scanner sc = new Scanner(System.in);
        do{
            view.showMenu();
            try {
                view.pringQueryResults(getResultOnUsersChoice(getUserChoice(sc),sc));
            } catch (WrongParameterFromConsoleException exc) {
                view.printMessage(exc.getMessage());
            } catch (MenuItemNotExistingExcpetion exc){
                view.printMessage(exc.getMessage());
            } finally {
                //LOGGING
            }
        }while(shouldProceed(sc));
    }

    private boolean shouldProceed(Scanner sc){
        view.printMessage("Would you like to proceed?(YES/NO)");
        while(sc.hasNextLine()){
            ShouldProceedEnum shouldProceed = ShouldProceedEnum.parseUserChoice(sc.nextLine());
            if(shouldProceed == ShouldProceedEnum.YES)
                return true;
            if(shouldProceed == ShouldProceedEnum.NO)
                return  false;

            view.printMessage(VehicleView.WRONG_INPUT_INT_DATA + VehicleView.RPT_INPUT);
        }
        return false;
    }

    private int getUserChoice(Scanner sc){
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
            case MenuItems.PLANES_WITH_HEIGHT_MORE_THAN_X_YEAR_AFTER_Y:
                int height = getParamFromConsole("Height value:", sc);
                int year = getParamFromConsole("Year value:", sc);
                return VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(model.getVehicles(), year, height);
            case MenuItems.NOT_PLANE_WITH_SPEED_BETWEEN_X_AND_Y:
                int minspeed = getParamFromConsole("Min speed", sc);
                int maxspeed = getParamFromConsole("Max speed", sc);
                return  VehicleFinder.getNotPlaneWithSpeedBetweenXAndY(model.getVehicles(), minspeed, maxspeed);
            case MenuItems.WITH_MAXIMAL_SPEED:
                return VehicleFinder.getWithMaximalSpeed(model.getVehicles());
            case MenuItems.WITH_MIN_PRICE_AND_MAX_SPEED_YOUNGER_THAN_X_YEARS:
                int ageLimit = getParamFromConsole("Age limit", sc);
                return  VehicleFinder.getWithMinPriceAndMaxSpeedYoungerThanXYears(model.getVehicles(), ageLimit);
            default:
                throw new MenuItemNotExistingExcpetion("Such menu item does not exist");
        }
    }

    private int getParamFromConsole(String msg, Scanner sc){
        view.printMessage(msg);
        while( ! sc.hasNextInt()) {
            view.printMessage(view.WRONG_INPUT_INT_DATA);
            view.printMessage(msg);
            sc.next();
        }
        return sc.nextInt();

    }

}
