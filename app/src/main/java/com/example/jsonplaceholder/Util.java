package com.example.jsonplaceholder;

import java.util.ArrayList;

public class Util {
    private Util() {
    }

    public static ArrayList<User> getTestUsers() {
        ArrayList<User> users = new ArrayList<>();
        //User(long id, String name, String username, String email, String phone, String website, Address address, Company company)
        users.add(new User(0, "Alexey Pertsukh",
                "mytest1_incomplete",null,
                "+38(068)908-29-21","plc-blog.com.ua",
                null, null));
        users.add(new User(0, "Daniil Laureanti",
                "mytest2_empty",null,
                null,null,
                null, null));
        return users;
    }

    public static String getValueOrEmptyStringIfNull(String value) {
        if(value == null) {
            return "";
        } else {
            return value;
        }
    }
}
