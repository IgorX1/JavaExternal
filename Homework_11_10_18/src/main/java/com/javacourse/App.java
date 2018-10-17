package com.javacourse;
import java.util.List;

public class App
{
    public static void main( String[] args )
    {
        VehicleView view = new VehicleView();
        VehicleModel model = new VehicleModel();
        VehicleController controller = new VehicleController(view, model);


        /*System.out.println("Query 1:");
        List<Vehicle> query = VehicleController.getPlanesWithHeightMoreThan5000YearAfter2000(vehicles);
        query.forEach(n-> System.out.println(n));

        System.out.println("Query 2:");
        query = VehicleController.getNotPlaneWithSpeedBetween200And500(vehicles);
        query.forEach(n-> System.out.println(n));

        FilteredResult query3 = VehicleController.getFilteredByWaysOfMOving(vehicles);
        System.out.println(query3);

        query = VehicleController.getWithMaximalSpeed(query3.flyable);
        System.out.println("Flyable with maximal speed:");
        query.forEach(n-> System.out.println(n));
        query = VehicleController.getWithMaximalSpeed(query3.rideable);
        System.out.println("Flyable with maximal rideable:");
        query.forEach(n-> System.out.println(n));
        query = VehicleController.getWithMaximalSpeed(query3.swimable);
        System.out.println("Swimable with maximal speed:");
        query.forEach(n-> System.out.println(n));*/
    }


}
