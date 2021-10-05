package com.example.jsonplaceholder;

import android.annotation.SuppressLint;

import java.io.Serializable;

public class Address implements Serializable {
    private String street;
    private String suite;
    private String city;
    private String zipcode;
    private Geo geo;

    public Address(String street, String suite, String city, String zipcode, Geo geo) {
        this.street = street;
        this.suite = suite;
        this.city = city;
        this.zipcode = zipcode;
        this.geo = geo;
    }

    private Address() {
        this("","","","", new Geo(0,0));
    }

    public static Address getInstanceNullObject() {
        return new Address();
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getSuite() {
        return suite;
    }

    public void setSuite(String suite) {
        this.suite = suite;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getZipcode() {
        return zipcode;
    }

    public void setZipcode(String zipcode) {
        this.zipcode = zipcode;
    }

    public String getGeoString() {
        return geo.getGeoString();
    }

    private static class Geo implements Serializable {
        private final double lat;
        private final double lng;

        public Geo(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        @SuppressLint("DefaultLocale")
        public String getGeoString() {
            return String.format("%f : %f", lat, lng);
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

}

