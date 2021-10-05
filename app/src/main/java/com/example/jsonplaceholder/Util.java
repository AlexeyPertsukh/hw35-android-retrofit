package com.example.jsonplaceholder;

import java.util.ArrayList;

public class Util {
    private Util() {
    }

    public static ArrayList<User> getTestUsers() {
        ArrayList<User> users = new ArrayList<>();
        //User(long id, String name, String username, String email, String phone, String website, Address address, Company company)
        users.add(new User(0, "Daniel Rany",
                "MyTest1 - incomplete",null,
                "+38(068)109-99-00","comp-man.info",
                null, null));
        users.add(new User(0, "Agata Kristy",
                "MyTest2 - all empty",null,
                null,null,
                null, null));
        return users;
    }
}
