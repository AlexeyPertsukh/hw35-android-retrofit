package com.example.jsonplaceholder.network;

import com.example.jsonplaceholder.utils.IConst;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class NetworkService implements IConst {
    private static NetworkService networkService;
    private final Retrofit retrofit;

    private NetworkService() {
        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static NetworkService getInstance() {
        if (networkService == null) {
            networkService = new NetworkService();
        }
        return networkService;
    }

    public Api getJsonApi() {
        return retrofit.create(Api.class);
    }

}
