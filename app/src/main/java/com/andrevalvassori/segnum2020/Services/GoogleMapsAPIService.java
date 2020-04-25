package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.location.LocationNewDTO;

import org.json.JSONArray;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface GoogleMapsAPIService {

    String key = "AIzaSyAoUiT36PwHhoGEEvcnAG8SG8I8VowfZkc";

//    @GET("location/event/{id}")
//    Call<Void> transateFromXY(@);

    @GET("?address={address}&key=" + key)
    Call<JSONObject> transateToXY(@Path("address") String address);
}
