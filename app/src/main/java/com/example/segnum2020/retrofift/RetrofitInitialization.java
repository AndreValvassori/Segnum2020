package com.example.segnum2020.retrofift;

import com.example.segnum2020.Services.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitialization {
    public final Retrofit retrofit;

    public RetrofitInitialization() {

        String url = "http://192.168.137.68:8080/";
         retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(JacksonConverterFactory.create()).build();
    }
    public UserService getUserService()
    {
        return retrofit.create(UserService.class);
    }

}