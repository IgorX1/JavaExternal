package com.javacourse;

public class App
{
    public static void main( String[] args )
    {
        Ship ship = new Ship.ShipBuilder(80, 1999, 25000)
                .numberOfPassengers(500)
                .port(12)
                .build();
        System.out.println(ship);

        Plane plane = new Plane.PlaneBuilder(350, 2010, 1000000)
                .numberOfPassengers(800)
                .height(12000)
                .build();
        System.out.println(plane);

        Vehicle p2 = plane.clone();
        System.out.println(p2.hashCode() +" "+plane.hashCode());
    }
}
