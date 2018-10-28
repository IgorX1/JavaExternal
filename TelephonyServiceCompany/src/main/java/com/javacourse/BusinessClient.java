package com.javacourse;

public class BusinessClient extends Client implements BusinessAllowable{

    public BusinessClient(String name, Tariff tariff) {
        super(name, tariff);
    }

    @Override
    public String toString() {
        final StringBuffer sb = new StringBuffer("BusinessClient{");
        sb.append('}');
        return sb.toString();
    }
}
