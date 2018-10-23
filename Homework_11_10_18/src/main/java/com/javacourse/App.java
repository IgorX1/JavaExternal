package com.javacourse;

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
