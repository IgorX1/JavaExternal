package com.javacourse;

import java.util.Objects;

class Client {
    protected String name;
    protected Tariff tariff;

    public Client(String name, Tariff tariff) {
        this.name = name;
        this.tariff = tariff;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Tariff getTariff() {
        return tariff;
    }

    public void setTariff(Tariff tariff) {
        this.tariff = tariff;
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("Client{");
        sb.append("name='").append(name).append('\'');
        sb.append(", tariff=").append(tariff);
        sb.append('}');
        return sb.toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client)) return false;
        Client client = (Client) o;
        return name.equals(client.name) &&
               tariff.equals(client.tariff);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, tariff);
    }
}
