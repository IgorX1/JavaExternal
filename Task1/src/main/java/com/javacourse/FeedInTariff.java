package com.javacourse;

import static com.javacourse.TariffPriceList.*;

public class FeedInTariff extends Tariff {

    static final FeedInTariff INSTANCE = new FeedInTariff(FEEDIN_TARIFF_PRICE);

    private FeedInTariff(double pricePerMonth) {
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
