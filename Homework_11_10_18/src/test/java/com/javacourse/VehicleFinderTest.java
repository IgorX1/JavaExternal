package com.javacourse;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class VehicleFinderTest {
    List<Vehicle> res = new ArrayList<>();

    @Before
    public void setUp() throws Exception {
        res.add(new Plane.PlaneBuilder(250, 2010, 1000)
                .numberOfPassengers(800)
                .height(12000)
                .build()
        );
        res.add(new Plane.PlaneBuilder(250, 1999, 1000000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        res.add(new Plane.PlaneBuilder(1100, 2005, 20000)
                .numberOfPassengers(800)
                .height(6000)
                .build()
        );
        res.add(new Ship.ShipBuilder(95, 2001, 300000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        res.add(new Ship.ShipBuilder(80, 2001, 1000000)
                .numberOfPassengers(800)
                .port(6000)
                .build()
        );
        res.add(new Car(120, 2013, 150000));
        res.add(new BatMobile(1200, 1985, 11111));
        res.add(new AmphibiousCar(110, 2018, 170000));
        res.add(new AmphibiousCar(250, 2018, 170000));
    }

    @Test
    public void isPlaneWithHeightMoreThanXYearAfterYReturnsExpectedResult() {
        assertArrayEquals(VehicleFinder.getPlanesWithHeightMoreThanXYearAfterY(res,
                2008, 5000).toArray(),
                new Plane[] {new Plane
                        .PlaneBuilder(250, 2010, 1000)
                        .numberOfPassengers(800)
                        .height(12000)
                        .build()});
    }
}