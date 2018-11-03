package com.javacourse;

public class Dock {
    private int id;
    private boolean isFree;

    public Dock(int id) {
        this.id = id;
        this.isFree = true;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFree() {
        return isFree;
    }

    public void setFree(boolean free) {
        isFree = free;
    }
}
