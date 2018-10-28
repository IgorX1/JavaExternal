package com.javacourse;

import java.math.BigDecimal;
import java.util.Objects;

abstract class Tariff {
    protected BigDecimal pricePerMonth;

    public Tariff(BigDecimal pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Tariff{");
        sb.append("pricePerMonth=").append(pricePerMonth);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tariff)) return false;
        Tariff tariff = (Tariff) o;
        return pricePerMonth.equals(tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pricePerMonth);
    }
}
