package com.andrevalvassori.segnum2020.retrofift;

import com.andrevalvassori.segnum2020.Services.EventService;
import com.andrevalvassori.segnum2020.Services.LocationService;
import com.andrevalvassori.segnum2020.Services.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitialization {
    public final Retrofit retrofit;

    public RetrofitInitialization() {

        String url = "http://192.168.0.100:8080/";
         retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(JacksonConverterFactory.create()).build();
    }
    public UserService getUserService()
    {
        return retrofit.create(UserService.class);
    }
    public EventService getEventService()
    {
        return retrofit.create(EventService.class);
    }
    public LocationService getLocationService()
    {
        return retrofit.create(LocationService.class);
    }

}