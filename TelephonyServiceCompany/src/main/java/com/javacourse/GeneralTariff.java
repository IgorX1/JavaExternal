package com.javacourse;

import static com.javacourse.TariffPriceList.*;

public class GeneralTariff extends Tariff {

    public static final GeneralTariff INSTANCE = new GeneralTariff(GENERAL_TARIFF_PRICE);

    private GeneralTariff(double pricePerMonth) {
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
