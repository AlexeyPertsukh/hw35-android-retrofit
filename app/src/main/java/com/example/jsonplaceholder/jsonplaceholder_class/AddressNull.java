package com.example.jsonplaceholder.jsonplaceholder_class;

public class AddressNull extends Address{
    private static AddressNull addressNull;

    private AddressNull() {
    }

    public static AddressNull getInstance() {
        if(addressNull == null) {
            addressNull = new AddressNull();
        }
        return addressNull;
    }

    @Override
    public boolean isNull() {
        return true;
    }

    @Override
    public String getStreet() {
        return EMPTY_STRING;
    }

    @Override
    public String getSuite() {
        return EMPTY_STRING;
    }

    @Override
    public String getCity() {
        return EMPTY_STRING;
    }

    @Override
    public String getZipcode() {
        return EMPTY_STRING;
    }

    @Override
    public String getGeoString() {
        return EMPTY_STRING;
    }
}
