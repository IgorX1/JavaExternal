package com.javacourse;

public class App
{
    public static void main( String[] args )
    {
        VehicleView view = new VehicleView();
        VehicleModel model = new VehicleModel();
        VehicleController controller = new VehicleController(view, model);
        controller.processUser();
    }


}
