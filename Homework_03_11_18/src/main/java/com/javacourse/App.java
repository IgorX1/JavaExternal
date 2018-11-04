package com.javacourse;

import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

public class App
{
    private static final Logger logger;
    static {
        logger = Logger.getLogger(Ship.class);
        DOMConfigurator.configure("log/log4j.xml");
    }

    public static void main( String[] args )
    {
        Harbor harbor = new Harbor("Odessa");

        Thread ship1 = new Thread(new Ship.ShipBuilder(1, harbor)
                .currentCapacity(3)
                .totalCapacity(5)
                .build(), "Ship1");
        Thread ship2 = new Thread(new Ship.ShipBuilder(2, harbor)
                .currentCapacity(0)
                .totalCapacity(5)
                .build(), "Ship2");
        Thread ship3 = new Thread(new Ship.ShipBuilder(3, harbor)
                .currentCapacity(2)
                .totalCapacity(5)
                .build(), "Ship3");
        Thread ship4 = new Thread(new Ship.ShipBuilder(4, harbor)
                .currentCapacity(0)
                .totalCapacity(5)
                .build(), "Ship4");
        Thread ship5 = new Thread(new Ship.ShipBuilder(5, harbor)
                .currentCapacity(4)
                .totalCapacity(5)
                .build(), "Ship5");

        ship1.start();
        ship2.start();
        ship3.start();
        ship4.start();
        ship5.start();

        try {
            ship1.join();
            ship2.join();
            ship3.join();
            ship4.join();
            ship5.join();
        } catch (InterruptedException e) {
            logger.error(e.getMessage());
        }
        System.out.printf("Working day finished. Harbor closed, all ships swam home.");
    }
}
