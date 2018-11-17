package com.javacourse.productModels;

import java.math.BigDecimal;
import java.util.Objects;

public class Laptop {
    private int code;
    private String model;
    private short speed;
    private short ram;
    private double hd;
    private BigDecimal price;
    private byte screen;

    public Laptop(int code, String model, short speed, short ram, double hd, BigDecimal price, byte screen) {
        this.code = code;
        this.model = model;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.price = price;
        this.screen = screen;
    }

    public Laptop() {
    }

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

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Laptop{");
        sb.append("code=").append(code);
        sb.append(", model='").append(model).append('\'');
        sb.append(", speed=").append(speed);
        sb.append(", ram=").append(ram);
        sb.append(", hd=").append(hd);
        sb.append(", price=").append(price);
        sb.append(", screen=").append(screen);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Laptop)) return false;
        Laptop laptop = (Laptop) o;
        return code == laptop.code &&
                speed == laptop.speed &&
                ram == laptop.ram &&
                Double.compare(laptop.hd, hd) == 0 &&
                screen == laptop.screen &&
                model.equals(laptop.model) &&
                price.equals(laptop.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, model, speed, ram, hd, price, screen);
    }
}
