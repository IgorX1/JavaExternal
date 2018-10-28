package com.javacourse;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TelephoneServiceController {
    private List<Client> clients = new LinkedList<>();

    private void fillClients(){
        clients.addAll(Arrays.asList(
          new Client("Ivan Ivanov", Tariffs.GENERAL),
          new Client("Petro Petrov", Tariffs.FEED_IN),
          new Client("Oleksandr Sashkin", Tariffs.BUSINESS)
        ));
    }
}

