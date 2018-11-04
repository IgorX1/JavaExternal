package com.javacourse;

/**
 * This class is immutable.
 * Thus its usage is safe.
 */
public class Dock {
    private int id;

    public Dock(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
