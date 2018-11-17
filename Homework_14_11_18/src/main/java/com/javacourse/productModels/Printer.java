package com.javacourse.productModels;

import java.math.BigDecimal;
import java.util.Objects;

public class Printer {
    private int code;
    private String model;
    private String color;
    private String type;
    private BigDecimal price;

    public Printer(int code, String model, String color, String type, BigDecimal price) {
        this.code = code;
        this.model = model;
        this.color = color;
        this.type = type;
        this.price = price;
    }

    public Printer() {
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

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Printer{");
        sb.append("code=").append(code);
        sb.append(", model='").append(model).append('\'');
        sb.append(", color='").append(color).append('\'');
        sb.append(", type='").append(type).append('\'');
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Printer)) return false;
        Printer printer = (Printer) o;
        return code == printer.code &&
                model.equals(printer.model) &&
                color.equals(printer.color) &&
                type.equals(printer.type) &&
                price.equals(printer.price);
    }

    @Override
    public int hashCode() {
        return Objects.hash(code, model, color, type, price);
    }
}
