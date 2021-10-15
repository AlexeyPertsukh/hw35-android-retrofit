package com.example.jsonplaceholder.jsonplaceholder_class;

import java.io.Serializable;

public class Address implements Serializable, INull {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    protected Address() {
    }

    @SuppressWarnings("unused")
    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }


    public static Address getInstanceNull() {
        return AddressNull.getInstance();
    }

    public String getStreet() {
        return getValueOrEmptyStringIfNull(street);
    }

    public String getSuite() {
        return getValueOrEmptyStringIfNull(suite);
    }

    public String getCity() {
        return getValueOrEmptyStringIfNull(city);
    }

    public String getZipcode() {
        return getValueOrEmptyStringIfNull(zipcode);
    }

    public String getGeoString() {
        return geo.getGeoString();
    }

    @Override
    public boolean isNull() {
        return false;
    }

}

