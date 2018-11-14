package com.javacourse;

import java.math.BigDecimal;

public class Laptop {
    private int code;
    private String model;
    private short speed;
    private short ram;
    private double hd;
    private BigDecimal price;
    private byte screen;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public short getSpeed() {
        return speed;
    }

    public void setSpeed(short speed) {
        this.speed = speed;
    }

    public short getRam() {
        return ram;
    }

    public void setRam(short ram) {
        this.ram = ram;
    }

    public double getHd() {
        return hd;
    }

    public void setHd(double hd) {
        this.hd = hd;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public byte getScreen() {
        return screen;
    }

    public void setScreen(byte screen) {
        this.screen = screen;
    }
}
