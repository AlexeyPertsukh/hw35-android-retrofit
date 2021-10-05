package com.example.jsonplaceholder;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface Api {
    @GET("/users")
    Call<List<User>> getAllUsers();
}
