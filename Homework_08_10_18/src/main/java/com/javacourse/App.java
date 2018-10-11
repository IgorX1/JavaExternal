package com.javacourse;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        TextFile a = new TextFile("123", "123");
        TextFile b = new TextFile("123", "123");
        System.out.println( a.equals(b) );
        System.out.println(a.hashCode());
        System.out.println(b.hashCode());
    }
}
