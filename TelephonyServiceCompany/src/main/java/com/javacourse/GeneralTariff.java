package com.javacourse;

import java.math.BigDecimal;

public class GeneralTariff extends Tariff {
    public GeneralTariff(double pricePerMonth) {
        super(pricePerMonth);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("GeneralTariff{");
        sb.append("pricePerMonth=").append(pricePerMonth);
        sb.append('}');
        return sb.toString();
    }
}
