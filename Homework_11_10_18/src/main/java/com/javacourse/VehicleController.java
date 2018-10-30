package com.javacourse;

import java.io.*;
import java.util.*;

import com.javacourse.exceptions.MenuItemNotExistingException;
import com.javacourse.exceptions.WrongParameterFromConsoleException;
import com.javacourse.serialization.ModelSerialization;
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
    private final Scanner sc;

    //Log4j logger
    private static Logger logger;

    static {
        logger = Logger.getLogger(VehicleController.class);
    }

    public VehicleController(VehicleView view, VehicleCollectionModel model) {
        this.view = view;
        this.model = model;
        deserializeModel();
        //fillVehiclesManually();
        //Set configuration file for the log4j logger
        DOMConfigurator.configure(LOG_CONFIG_PATH);
        sc = new Scanner(System.in);
    }

    private void fillVehiclesManually(){
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

    private void deserializeModel(){
        ModelSerialization modelSerialization = new ModelSerialization();
        ArrayList<Vehicle> vehicles = new ArrayList<>();
        try {
            vehicles = modelSerialization.deserializeModel(SERIALIZED_DATA_PATH);
        } catch (IOException e) {
            logger.debug(e.getMessage()+" Deserialization of model failed");
        }
        model.setVehicles(vehicles);
    }

    public void processUser(){
        String language = getLanguageFromConsole();
        localizeProgram(language);
        String proceed;
        do{
            view.showMenu();
            try {
                int menuItem = getUserChoiceFromConsole();
                view.printQueryResults(getResultOnUsersChoice(menuItem));
            } catch (WrongParameterFromConsoleException
                    | MenuItemNotExistingException
                    | NumberFormatException exc) {
                view.printError(resourceBundle.getString("data.inputError"));
                logger.debug(exc.getMessage());
            }
            proceed = askUserFromConsoleIfShouldProceed();
        }while(shouldProceed(proceed));
    }

    void localizeProgram(String lang) {
        locale = getLocaleBasedOnUsersChoice(lang);
        resourceBundle = getResourceBundleBasedOnUsersChoice(locale);
        view.setResourceBundle(resourceBundle);
    }

    private String getLanguageFromConsole(){
        view.printLanguageLocalization();
        return sc.nextLine();
    }

    Locale getLocaleBasedOnUsersChoice(String language) {
        return new Locale(parseLanguageChoice(language).getLangCode());
    }

    private ResourceBundle getResourceBundleBasedOnUsersChoice(Locale locale) {
        return ResourceBundle.getBundle(LOCAL_BUNDLE_NAME, locale);
    }


    LanguageEnum parseLanguageChoice(String language){
        switch (language){
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

    private String askUserFromConsoleIfShouldProceed(){
        view.printMessage(resourceBundle.getString("data.shouldProceed")+"(YES/NO)");
        String input = sc.nextLine();
        view.printMessage(resourceBundle.getString("data.wrongInput"));
        return input;
    }

    boolean shouldProceed(String proceed){
        ShouldProceedEnum shouldProceed = ShouldProceedEnum.parseUserChoice(proceed);
        if(shouldProceed == ShouldProceedEnum.YES)
            return true;
        return false;
    }

    private int getUserChoiceFromConsole(){
        view.printMessage(resourceBundle.getString("data.chooseTheMenuItem"));
        return Integer.parseInt(sc.nextLine());
    }

     private List<Vehicle> getResultOnUsersChoice(int choice){
        switch (choice){
            case MenuItems.PLANES_WITH_HEIGHT_MORE_THAN_X_YEAR_AFTER_Y:
                int height = getParamFromConsole(resourceBundle.getString("data.heightValue")+":");
                checkHeight(height);
                int year = getParamFromConsole(resourceBundle.getString("data.yearValue")+":");
                checkYear(year);
                return VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(model.getVehicles(), year, height);
            case MenuItems.NOT_PLANE_WITH_SPEED_BETWEEN_X_AND_Y:
                int minSpeed = getParamFromConsole(resourceBundle.getString("data.minSpeed")+":");
                checkSpeed(minSpeed);
                int maxSpeed = getParamFromConsole(resourceBundle.getString("data.maxSpeed")+":");
                checkSpeed(maxSpeed);
                checkFirstParamIsLessThanSecond(minSpeed, maxSpeed);
                return  VehicleFinder.getNotPlaneWithSpeedBetweenXAndY(model.getVehicles(), minSpeed, maxSpeed);
            case MenuItems.WITH_MAXIMAL_SPEED:
                return VehicleFinder.getWithMaximalSpeed(model.getVehicles());
            case MenuItems.WITH_MIN_PRICE_AND_MAX_SPEED_YOUNGER_THAN_X_YEARS:
                int ageLimit = getParamFromConsole(resourceBundle.getString("data.ageLimit")+":");
                checkAge(ageLimit);
                return  VehicleFinder.getWithMinPriceAndMaxSpeedYoungerThanXYears(model.getVehicles(), ageLimit);
            default:
                throw new MenuItemNotExistingException("Such menu item does not exist");
        }
    }

    private int getParamFromConsole(String msg){
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
