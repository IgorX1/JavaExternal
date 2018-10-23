package com.javacourse;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

class VehicleView {
    static private ResourceBundle resourceBundle;

    //In case custom locale not chosen, the default locale will be used
    static {
        resourceBundle = ResourceBundle.getBundle("dictionary");
    }

    static void setResourceBundle(ResourceBundle resourceBundle) {
        VehicleView.resourceBundle = resourceBundle;
    }

    void printQueryResults(List<Vehicle> vehicles){
        vehicles.forEach(System.out::println);
    }

    void printMessage(String message){
        System.out.println(message);
    }

    void printError(String message){
        System.err.println(message);
    }

    void printLanguageLocalization(){
        printMessage("Choose language/Выберите язык/Оберіть мову");
        printMessage("1-English");
        printMessage("2-Русский");
        printMessage("3-Українська");
    }

    void showMenu(){
        System.out.println(resourceBundle.getString("data.menuHeader"));
        System.out.println(resourceBundle.getString("data.menuItem1"));
        System.out.println(resourceBundle.getString("data.menuItem2"));
        System.out.println(resourceBundle.getString("data.menuItem3"));
        System.out.println(resourceBundle.getString("data.menuItem4"));
    }

}
