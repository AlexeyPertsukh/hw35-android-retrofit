package com.example.jsonplaceholder;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class User implements Serializable, INullable {
    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("username")
    @Expose
    private String username;

    @SerializedName("email")
    @Expose
    private String email;

    @SerializedName("phone")
    @Expose
    private String phone;

    @SerializedName("website")
    @Expose
    private String website;

    @SerializedName("address")
    @Expose
    private Address address;

    @SerializedName("company")
    @Expose
    private Company company;

//    public User() {
//        this.address = Address.getInstanceNullObject();
//        this.company = Company.getInstanceNullObject();
//    }

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
        if(address != null) {
            return address;
        } else {
            return Address.getInstanceNullObject();
        }
    }

    public Company getCompany() {
        if(company != null) {
            return company;
        } else {
            return Company.getInstanceNullObject();
        }
    }

}

