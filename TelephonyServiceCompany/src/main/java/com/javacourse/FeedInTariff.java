package com.javacourse;

import java.math.BigDecimal;

public class FeedInTariff extends Tariff {
    public FeedInTariff(double pricePerMonth) {
        super(pricePerMonth);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("FeedInTariff{");
        sb.append("pricePerMonth=").append(pricePerMonth);
        sb.append('}');
        return sb.toString();
    }
}
