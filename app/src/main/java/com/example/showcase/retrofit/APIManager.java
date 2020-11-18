package com.example.showcase.retrofit;

import com.example.showcase.config.Constant;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class APIManager {
    private static APIManager INSTANCE;
    private RequestAPI requestAPI;

    private APIManager(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constant.URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        requestAPI = retrofit.create(RequestAPI.class);
    }
    public static APIManager getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new APIManager();
        }
        return INSTANCE;
    }

    public RequestAPI getRequestAPI() {
        return requestAPI;
    }
}
