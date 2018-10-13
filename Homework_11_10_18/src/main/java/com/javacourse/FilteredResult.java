package com.javacourse;

import java.util.ArrayList;
import java.util.List;

public class FilteredResult {
    public List<Vehicle> flyable = new ArrayList<>();
    public List<Vehicle> rideable = new ArrayList<>();
    public List<Vehicle> swimable = new ArrayList<>();

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer();
        sb.append("flyable:");
        flyable.forEach(x->sb.append(x));
        sb.append("\n rideable:");
        rideable.forEach(x->sb.append(x));
        sb.append("\n swimable:");
        swimable.forEach(x->sb.append(x));
        return sb.toString();
    }
}
