package com.javacourse;

public enum Tariffs {
    GENERAL(new GeneralTariff(100)),
    FEED_IN(new FeedInTariff(55)),
    BUSINESS(new BusinessTariff(750));

    Tariffs(Tariff tariff) {
        this.tariff = tariff;
    }

    private Tariff tariff;

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }
}
