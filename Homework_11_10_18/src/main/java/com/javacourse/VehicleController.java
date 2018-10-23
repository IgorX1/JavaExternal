package com.javacourse;

import java.util.*;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;
import static com.javacourse.ConstantValues.*;

public class VehicleController {

    private static final int STARTING_YEAR = 1900;

    //MVC architecture entities
    private VehicleView view;
    private VehicleCollectionModel model;

    //localization entities
    private Locale locale;
    private ResourceBundle resourceBundle;

    //Log4j logger
    private static Logger logger;

    static {
        logger = Logger.getLogger(VehicleController.class);
    }

    public VehicleController(VehicleView view, VehicleCollectionModel model) {
        this.view = view;
        this.model = model;
        fillVehicles();

        //Set configuration file for the log4j logger
        DOMConfigurator.configure("log/log4j.xml");
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
        localizeLanguage(sc);
        do{
            view.showMenu();
            try {
                view.printQueryResults(getResultOnUsersChoice(getUserChoice(sc),sc));
            } catch (WrongParameterFromConsoleException
                    | MenuItemNotExistingExcpetion
                    | NumberFormatException exc) {
                view.printError(resourceBundle.getString("data.inputError"));
                logger.debug(exc.getMessage());
            }
        }while(shouldProceed(sc));
    }

    private void localizeLanguage(Scanner sc) {
        view.printMessage("Choose language/Выберите язык/Оберіть мову");
        view.printMessage("1-English");
        view.printMessage("2-Русский");
        view.printMessage("3-Українська");
        locale = new Locale(getAndParseLanguageChoice(sc).getLangCode());
        resourceBundle = ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, locale);
        view.setResourceBundle(resourceBundle);
    }

    LanguageEnum getAndParseLanguageChoice(Scanner sc){
        String langChoice = sc.nextLine();
        switch (langChoice){
            case "1":
                return LanguageEnum.EN;
            case "2":
                return LanguageEnum.RU;
            case "3":
                return LanguageEnum.UA;
            default:
                return LanguageEnum.EN;
        }
    }

    private boolean shouldProceed(Scanner sc){
        view.printMessage(resourceBundle.getString("data.shouldProceed")+"(YES/NO)");
        while(sc.hasNextLine()){
            ShouldProceedEnum shouldProceed = ShouldProceedEnum.parseUserChoice(sc.nextLine());
            if(shouldProceed == ShouldProceedEnum.YES)
                return true;
            if(shouldProceed == ShouldProceedEnum.NO)
                return  false;

            view.printMessage(resourceBundle.getString("data.wrongInput"));
        }
        return false;
    }

    private int getUserChoice(Scanner sc){
        view.printMessage(resourceBundle.getString("data.chooseTheMenuItem"));
        int choice = Integer.parseInt(sc.nextLine());
        if(choice<LEAST_MENU_ITEM || choice>GREATEST_MENU_ITEM)
            throw new MenuItemNotExistingExcpetion("Such menu item does not exist");

        return choice;
    }

     List<Vehicle> getResultOnUsersChoice(int choice, Scanner sc){
        switch (choice){
            case MenuItems.PLANES_WITH_HEIGHT_MORE_THAN_X_YEAR_AFTER_Y:
                int height = getParamFromConsole(resourceBundle.getString("data.heightValue")+":", sc);
                checkHeight(height);
                int year = getParamFromConsole(resourceBundle.getString("data.yearValue")+":", sc);
                checkYear(year);
                return VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(model.getVehicles(), year, height);
            case MenuItems.NOT_PLANE_WITH_SPEED_BETWEEN_X_AND_Y:
                int minSpeed = getParamFromConsole(resourceBundle.getString("data.minSpeed")+":", sc);
                checkSpeed(minSpeed);
                int maxSpeed = getParamFromConsole(resourceBundle.getString("data.maxSpeed")+":", sc);
                checkSpeed(maxSpeed);
                checkFirstParamIsLessThanSecond(minSpeed, maxSpeed);
                return  VehicleFinder.getNotPlaneWithSpeedBetweenXAndY(model.getVehicles(), minSpeed, maxSpeed);
            case MenuItems.WITH_MAXIMAL_SPEED:
                return VehicleFinder.getWithMaximalSpeed(model.getVehicles());
            case MenuItems.WITH_MIN_PRICE_AND_MAX_SPEED_YOUNGER_THAN_X_YEARS:
                int ageLimit = getParamFromConsole(resourceBundle.getString("data.ageLimit")+":", sc);
                checkAge(ageLimit);
                return  VehicleFinder.getWithMinPriceAndMaxSpeedYoungerThanXYears(model.getVehicles(), ageLimit);
            default:
                throw new MenuItemNotExistingExcpetion("Such menu item does not exist");
        }
    }

    private int getParamFromConsole(String msg, Scanner sc){
        view.printMessage(msg);
        int choice = 0;
        try {
            choice = Integer.parseInt(sc.nextLine());
        } catch (NumberFormatException e) {
            throw new WrongParameterFromConsoleException("Expected numeric format");
        }
        return choice;
    }

    void checkHeight(int param){
        final int HEIGHT_MAX = 100000;
        final int HEIGHT_MIN = 500;
        if(param<=HEIGHT_MIN || param>=HEIGHT_MAX)
            throw new WrongParameterFromConsoleException("The height parameter has inadequate value");
    }

    void checkYear(int param){
        final int YEAR_MAX = Calendar.getInstance().get(Calendar.YEAR);;
        final int YEAR_MIN = STARTING_YEAR;
        if(param<=YEAR_MIN || param>=YEAR_MAX)
            throw new WrongParameterFromConsoleException("The year parameter has inadequate value");
    }

    void checkSpeed(int param){
        final int SPEED_MAX = 2000;
        final int SPEED_MIN = 20;
        if(param<=SPEED_MIN || param>=SPEED_MAX)
            throw new WrongParameterFromConsoleException("The speed parameter has inadequate value");
    }

    void checkAge(int param){
        final int AGE_MAX = Calendar.getInstance().get(Calendar.YEAR) - STARTING_YEAR;
        final int AGE_MIN = 0;
        if(param<=AGE_MIN || param>=AGE_MAX)
            throw new WrongParameterFromConsoleException("The age parameter has inadequate value");
    }

    void checkFirstParamIsLessThanSecond(int first, int second){
        if(first>second)
            throw new WrongParameterFromConsoleException("First parameter can't be greater than the second one");
    }
}
