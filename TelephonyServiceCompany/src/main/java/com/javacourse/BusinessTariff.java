package com.javacourse;

import static com.javacourse.TariffPriceList.*;

public class BusinessTariff extends Tariff {

    static final BusinessTariff INSTANCE = new BusinessTariff(BUSINESS_TARIFF_PRICE);

    private BusinessTariff(double pricePerMonth) {
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
