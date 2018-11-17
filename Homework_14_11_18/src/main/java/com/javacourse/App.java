package com.javacourse;

public class App 
{
    public static void main( String[] args )
    {
        LaptopDAO dao = new LaptopDAO();
        System.out.println(dao.getLaptops());
    }
}
