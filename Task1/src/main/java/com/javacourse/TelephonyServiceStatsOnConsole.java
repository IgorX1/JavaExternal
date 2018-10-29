package com.javacourse;

import java.util.List;
import java.util.Scanner;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class TelephonyServiceStatsOnConsole {

    private TelephonyServiceUtils utils;
    private Scanner scanner;

    //Log4j logger
    private static Logger logger;

    static {
        logger = Logger.getLogger(TelephonyServiceStatsOnConsole.class);
    }

    public TelephonyServiceStatsOnConsole() {
        this.utils = new TelephonyServiceUtils();
        this.scanner = new Scanner(System.in);
        //Set configuration file for the log4j logger
        DOMConfigurator.configure("log/log4j.xml");
    }

    public void processUser(){
        showMainStats();
        handleUsersRequestOfTariffsByPrice();
    }

    private void showMainStats(){
        showMessage("Statistics of the telephony service company\n");
        showMessage("Sorted tariffs by price per month:");
        showMessage(utils.getSortedTariffListByPrice());
        showMessage("\nTotal number of clients:");
        showMessage(utils.getTotalNumberOfClients());
    }

    private void handleUsersRequestOfTariffsByPrice(){
        try {
            if(doesUserWantToMakeARequestForTariffs()){
                double min = getParamFromConsole("Enter min price:");
                double max = getParamFromConsole("Enter max price:");
                showMessage("Tariffs with price in segment ["+min+";"+max+"]");
                showMessage(formatResultingMessageWithTariffsByMinMax(min,max));
            }
        } catch (NumberFormatException e) {
            logger.debug(e.getMessage());
            System.out.println("Wrong input");
        }
    }

    private String formatResultingMessageWithTariffsByMinMax(double min, double max){
        List result = utils.getTariffsByPriceLimits(min, max);
        if(min>max) throw new NumberFormatException();
        return result.size()>0 ? result.toString() : "Nothing found";
    }

    private boolean doesUserWantToMakeARequestForTariffs(){
        showMessage("Would you like to see tariff list filtered by price limits?");
        showMessage("[Y-yes]");
        showMessage("[Any button-no]");
        return scanner.nextLine().equals("Y");
    }

    private double getParamFromConsole(String message){
        showMessage(message);
        return Double.parseDouble(scanner.nextLine());
    }

    private void showMessage(String msg){
        System.out.println(msg);
    }

    private void showMessage(int msg){
        System.out.println(msg);
    }

    private void showMessage(List collection){
        collection.forEach(System.out::println);
    }
}
