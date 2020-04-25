package com.andrevalvassori.segnum2020.retrofift;

import com.andrevalvassori.segnum2020.Services.EventService;
import com.andrevalvassori.segnum2020.Services.GoogleMapsAPIService;
import com.andrevalvassori.segnum2020.Services.LocationService;
import com.andrevalvassori.segnum2020.Services.UserService;

import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class RetrofitInitialization {
    public final Retrofit retrofit;
    public String type;

    public RetrofitInitialization() {
        type = "self";
        String url = "http://felipejunges.com.br:8080/";
        retrofit = new Retrofit.Builder().baseUrl(url).addConverterFactory(JacksonConverterFactory.create()).build();
    }
    public RetrofitInitialization(String url) {
        this.type = "none";
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

    public GoogleMapsAPIService getGoogleMapsAPIService()
    {
        return retrofit.create(GoogleMapsAPIService.class);
    }

}