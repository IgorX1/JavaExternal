package com.javacourse;

import java.util.*;

public class TelephonyServiceUtils {
    private List<Tariff> tariffs;
    private List<Client> clients;

    public TelephonyServiceUtils() {
        tariffs = new LinkedList<>();
        clients = new LinkedList<>();
        fillBasicSetOfClients();
        fillBasicSetOfTariffs();
    }

    private void fillBasicSetOfClients(){
        clients.addAll(Arrays.asList(
            new Client("Ivan Ivanov", GeneralTariff.INSTANCE),
            new Client("John Thomson", BusinessTariff.INSTANCE),
            new Client("Eliza White", FeedInTariff.INSTANCE)
        ));
    }

    private void fillBasicSetOfTariffs(){
        tariffs.addAll(Arrays.asList(
             BusinessTariff.INSTANCE,
             GeneralTariff.INSTANCE,
             FeedInTariff.INSTANCE
        ));
    }

    public List<Tariff> getTariffs() {
        return tariffs;
    }

    public boolean addClient(Client c){
        return clients.add(c);
    }

    public boolean clearClientList(){
        if(clients.size()>0){
            clients.clear();
            return true;
        }
        return  false;
    }

    int getTotalNumberOfClients(){
        return clients.size();
    }

    List<Tariff> getSortedTariffListByPrice(){
        List<Tariff> sortedList = new ArrayList<>(tariffs);
        Collections.sort(sortedList,
                (x1, x2)-> (int)(x1.pricePerMonth-x2.pricePerMonth));
        return sortedList;
    }

    List<Tariff> getTariffsByPriceLimits(double minPrice, double maxPrice){
        List<Tariff> result = new ArrayList<>();
        tariffs.forEach(x->{
            if(doesPriceCorrespondToPriceLimits(x.pricePerMonth, minPrice, maxPrice)){
                result.add(x);
            }
        });
        return result;
    }

    boolean doesPriceCorrespondToPriceLimits(double actual, double min, double max){
        if(actual>=min && actual<=max)
            return true;
        else return false;
    }
}
