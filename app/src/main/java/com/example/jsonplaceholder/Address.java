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
        this.geo = Geo.getInstanceNullObject();
    }

    public static Address getInstanceNullObject() {
        return new Address();
    }

    public String getStreet() {
        return Util.getValueOrEmptyStringIfNull(street);
    }

    public String getSuite() {
        return Util.getValueOrEmptyStringIfNull(suite);
    }

    public String getCity() {
        return Util.getValueOrEmptyStringIfNull(city);
    }

    public String getZipcode() {
        return Util.getValueOrEmptyStringIfNull(zipcode);
    }

    public String getGeoString() {
        return geo.getGeoString();
    }

    //Geo
    private static class Geo implements Serializable {
        private double lat;
        private double lng;
        private boolean isNull;

        private Geo() {
        }

        public Geo(double lat, double lng) {
            this.lat = lat;
            this.lng = lng;
        }

        public static Geo getInstanceNullObject() {
            Geo geo = new Geo();
            geo.isNull = true;
            return geo;
        }

        @SuppressLint("DefaultLocale")
        public String getGeoString() {
            if(isNull) {
                return "";
            } else {
                return String.format("%f : %f", lat, lng);
            }
        }

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

}

