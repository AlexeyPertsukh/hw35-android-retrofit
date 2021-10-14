package com.example.jsonplaceholder;

import java.io.Serializable;

public class Company implements Serializable, INullable {
    private String name;
    private String catchPhrase;
    private String bs;

    private Company() {
    }

    public Company(String name, String catchPhrase, String bs) {
        this.name = name;
        this.catchPhrase = catchPhrase;
        this.bs = bs;
    }

    public static Company getInstanceNullObject() {
        return new Company();
    }

    public String getName() {
        return getValueOrEmptyStringIfNull(name);
    }

    public String getCatchPhrase() {
        return getValueOrEmptyStringIfNull(catchPhrase);
    }

    public String getBs() {
        return getValueOrEmptyStringIfNull(bs);
    }

}

