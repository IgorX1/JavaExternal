package com.javacourse;

public class App 
{
    public static void main( String[] args )
    {
        Harbor harbor = new Harbor();

        Thread ship1 = new Thread(new Ship.ShipBuilder(1)
                .currentCapacity(3)
                .totalCapacity(5)
                .build(), "Ship1");
        Thread ship2 = new Thread(new Ship.ShipBuilder(2)
                .currentCapacity(5)
                .totalCapacity(5)
                .build(), "Ship2");
        Thread ship3 = new Thread(new Ship.ShipBuilder(3)
                .currentCapacity(0)
                .totalCapacity(5)
                .build(), "Ship3");
        Thread ship4 = new Thread(new Ship.ShipBuilder(4)
                .currentCapacity(2)
                .totalCapacity(5)
                .build(), "Ship4");
        Thread ship5 = new Thread(new Ship.ShipBuilder(5)
                .currentCapacity(4)
                .totalCapacity(5)
                .build(), "Ship5");

        ship1.start();
        ship2.start();
        ship3.start();
        ship4.start();
        ship5.start();
    }
}
