package com.javacourse;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class VehicleCollectionModelTest {

    VehicleCollectionModel model;
    private static final int DEFAULT_SPEED = 1000;
    private static final int DEFAULT_YEAR = 2000;
    private static final int DEFAULT_PRICE = 10000;

    @Before
    public void setUp() throws Exception {
        model = new VehicleCollectionModel();
    }

    @After
    public void tearDown() throws Exception {
        model = null;
    }

    @Test
    public void getVehicles() {
    }

    @Test
    public void clearVehicles_emptyModel_returnsFalse() {
        assertFalse(model.clearVehicles());
        model.add(new AmphibiousCar(DEFAULT_SPEED, DEFAULT_YEAR, DEFAULT_PRICE));
        assertTrue(model.clearVehicles());
    }

    @Test
    public void clearVehicles_empty_returnsFalse(){
        assertFalse(model.clearVehicles());
    }

    @Test
    public void add_null_returnsFalse() {
        assertTrue(model.add(new AmphibiousCar(DEFAULT_SPEED, DEFAULT_YEAR, DEFAULT_PRICE)));
        assertFalse(model.add(null));
    }
}