package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("user")
    Call<Void> postRegister(@Body Object object);

    @GET("user/{id}")
    Call<UserDTO> getUser(@Path("id") Integer id);

    @POST("user/login")
    Call<UserDTO> postUserLogin(@Body Object object);

    @GET("user/{id}/events")
    Call<List<EventDTO>> getMyEvents(@Path("id") Integer id);
}
