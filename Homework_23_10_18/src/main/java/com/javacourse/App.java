package com.javacourse;

public class App 
{
    public static void main( String[] args )
    {
        CalculationView view = new CalculationView();
        CalculationController controller = new CalculationController(view);
        controller.processUser();
    }
}
