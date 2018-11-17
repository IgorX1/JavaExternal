package com.javacourse.productModels;

import java.math.BigDecimal;
import java.util.Objects;

public class PC {
    private int code;
    private String model;
    private short speed;
    private short ram;
    private double hd;
    private BigDecimal price;
    private String cd;

    public PC(int code, String model, short speed, short ram, double hd, BigDecimal price, String cd) {
        this.code = code;
        this.model = model;
        this.speed = speed;
        this.ram = ram;
        this.hd = hd;
        this.price = price;
        this.cd = cd;
    }

    public PC() {
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

    public String getCd() {
        return cd;
    }

    public void setCd(String cd) {
        this.cd = cd;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("PC{");
        sb.append("code=").append(code);
        sb.append(", model='").append(model).append('\'');
        sb.append(", speed=").append(speed);
        sb.append(", ram=").append(ram);
        sb.append(", hd=").append(hd);
        sb.append(", price=").append(price);
        sb.append(", cd='").append(cd).append('\'');
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof PC)) return false;
        PC pc = (PC) o;
        return code == pc.code &&
                speed == pc.speed &&
                ram == pc.ram &&
                Double.compare(pc.hd, hd) == 0 &&
                model.equals(pc.model) &&
                price.equals(pc.price) &&
                cd.equals(pc.cd);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, model, speed, ram, hd, price, cd);
    }
}
