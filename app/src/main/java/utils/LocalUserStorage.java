package utils;

import java.util.ArrayList;

import jsonplaceholder_class.User;

public class LocalUserStorage {
    private LocalUserStorage() {
    }

    //юзеры с частичным заполнением инфо для тестирования вывода на gui
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

}
