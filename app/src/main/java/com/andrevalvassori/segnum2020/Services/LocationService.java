package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.DTO.location.LocationNewDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface LocationService {

    @POST("location/event/{id}")
    Call<Void> postLocation(@Path("id") Integer id, @Body Object object);

    @POST("location/user/{id}")
    Call<Void> postUserLocation(@Path("id") Integer id, @Body LocationNewDTO object);

    @PUT("location/{id}")
    Call<Void> putLocation(@Path("id") Integer id, @Body LocationNewDTO object);

}
