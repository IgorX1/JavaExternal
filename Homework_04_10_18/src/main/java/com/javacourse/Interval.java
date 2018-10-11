package com.javacourse;

public class Interval {
    private int start;
    private int end;

    public Interval(int s, int e){
        start = s;
        end = e;
    }

    public int getStart(){
        return start;
    }

    public int getEnd(){
        return end;
    }

    public int getLength(){
        if(start>=0 && end>=0)
            return end-start+1;
        else return Math.abs(start)+Math.abs(end)+1;
    }
}
