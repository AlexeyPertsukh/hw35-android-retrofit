package com.example.jsonplaceholder;

import java.io.Serializable;

public class Company implements Serializable {
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
        return Util.getValueOrEmptyStringIfNull(name);
    }

    public String getCatchPhrase() {
        return Util.getValueOrEmptyStringIfNull(catchPhrase);
    }

    public String getBs() {
        return Util.getValueOrEmptyStringIfNull(bs);
    }
}

