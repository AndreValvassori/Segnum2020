package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.UserDTO;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface UserService {

    @POST("user")
    Call<Object> postRegister(@Body Object object);

    @GET("user/{id}")
    Call<UserDTO> getUser(@Path("id") Integer id);

}
