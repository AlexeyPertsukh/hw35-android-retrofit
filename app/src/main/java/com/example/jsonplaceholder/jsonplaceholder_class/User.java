package com.example.jsonplaceholder.jsonplaceholder_class;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable, INull {
    @SerializedName("id")
    @Expose
    private final long id;

    @SerializedName("name")
    @Expose
    private final String name;

    @SerializedName("username")
    @Expose
    private final String username;

    @SerializedName("email")
    @Expose
    private final String email;

    @SerializedName("phone")
    @Expose
    private final String phone;

    @SerializedName("website")
    @Expose
    private final String website;

    @SerializedName("address")
    @Expose
    private final Address address;

    @SerializedName("company")
    @Expose
    private final Company company;

    public User(long id, String name, String username, String email, String phone, String website, Address address, Company company) {
        this.id = id;
        this.name = name;
        this.username = username;
        this.email = email;
        this.phone = phone;
        this.website = website;
        this.address = address;
        this.company = company;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return getValueOrEmptyStringIfNull(name);
    }

    public String getUsername() {
        return getValueOrEmptyStringIfNull(username);
    }

    public String getEmail() {
        return getValueOrEmptyStringIfNull(email);
    }

    public String getPhone() {
        return getValueOrEmptyStringIfNull(phone);
    }

    public String getWebsite() {
        return getValueOrEmptyStringIfNull(website);
    }

    public Address getAddress() {
        if (address == null) {
            return Address.getInstanceNull();
        } else {
            return address;
        }
    }

    public Company getCompany() {
        if(company == null) {
            return Company.getInstanceNull();
        } else {
            return company;
        }
    }

    @Override
    public boolean isNull() {
        return false;
    }
}

