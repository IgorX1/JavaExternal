package com.javacourse;

import java.math.BigDecimal;

public class BusinessTariff extends Tariff {

    public BusinessTariff(double pricePerMonth) {
        super(pricePerMonth);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BusinessTariff{");
        sb.append("pricePerMonth=").append(pricePerMonth);
        sb.append('}');
        return sb.toString();
    }
}
