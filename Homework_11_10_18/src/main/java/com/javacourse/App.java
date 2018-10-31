package com.javacourse;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        VehicleView view = new VehicleView();
        VehicleCollectionModel model = new VehicleCollectionModel();
        VehicleController controller = new VehicleController(view, model);
        controller.processUser();
    }
}
