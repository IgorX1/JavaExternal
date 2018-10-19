package com.javacourse;

import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class VehicleController {

    private VehicleView view;
    private VehicleModel model;
    private static Logger logger = Logger.getLogger(VehicleController.class);

    public VehicleController(VehicleView view,VehicleModel model) {
        this.view = view;
        this.model = model;
        DOMConfigurator.configure("log4j.xml");
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
            } catch (WrongParameterFromConsoleException
                    | MenuItemNotExistingExcpetion exc) {
                view.printError("User input problem has occured.");
                logger.debug(exc.getMessage());
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
                chechHeight(height);
                int year = getParamFromConsole("Year value:", sc);
                chechYear(year);
                return VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(model.getVehicles(), year, height);
            case MenuItems.NOT_PLANE_WITH_SPEED_BETWEEN_X_AND_Y:
                int minspeed = getParamFromConsole("Min speed", sc);
                chechSpeed(minspeed);
                int maxspeed = getParamFromConsole("Max speed", sc);
                chechSpeed(maxspeed);
                checkFirstParamIsLessThanSecond(minspeed, maxspeed);
                return  VehicleFinder.getNotPlaneWithSpeedBetweenXAndY(model.getVehicles(), minspeed, maxspeed);
            case MenuItems.WITH_MAXIMAL_SPEED:
                return VehicleFinder.getWithMaximalSpeed(model.getVehicles());
            case MenuItems.WITH_MIN_PRICE_AND_MAX_SPEED_YOUNGER_THAN_X_YEARS:
                int ageLimit = getParamFromConsole("Age limit", sc);
                chechAge(ageLimit);
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

    private void chechHeight(int param){
        final int HEIGHT_MAX = 100000;
        final int HEIGHT_MIN = 500;
        if(param<=HEIGHT_MIN || param>=HEIGHT_MAX)
            throw new WrongParameterFromConsoleException("The height parameter has inadequate value");
    }

    private void chechYear(int param){
        final int YEAR_MAX = Calendar.getInstance().get(Calendar.YEAR);;
        final int YEAR_MIN = 1900;
        if(param<=YEAR_MIN || param>=YEAR_MAX)
            throw new WrongParameterFromConsoleException("The year parameter has inadequate value");
    }

    private void chechSpeed(int param){
        final int SPEED_MAX = 2000;
        final int SPEED_MIN = 20;
        if(param<=SPEED_MIN || param>=SPEED_MAX)
            throw new WrongParameterFromConsoleException("The speed parameter has inadequate value");
    }

    private void chechAge(int param){
        final int AGE_MAX = Calendar.getInstance().get(Calendar.YEAR) - 1900;
        final int AGE_MIN = 0;
        if(param<=AGE_MIN || param>=AGE_MAX)
            throw new WrongParameterFromConsoleException("The age parameter has inadequate value");
    }

    private void checkFirstParamIsLessThanSecond(int first, int second){
        if(first>second)
            throw new WrongParameterFromConsoleException("First parameter can't be greater than the second one");
    }
}
