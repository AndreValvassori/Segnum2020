package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface EventService {

    @GET("event")
    Call<List<EventDTO>> getAllEvents();

    @POST("event")
    Call<Void> postEvent(@Body Object object);
    //@GET("")

//    @GET("user/{id}")
//    Call<UserDTO> getUser(@Path("id") Integer id);
//
//    @POST("user/login")
//    Call<UserDTO> postUserLogin(@Body Object object);
}

