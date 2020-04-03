package com.andrevalvassori.segnum2020.Services;

import com.andrevalvassori.segnum2020.DTO.event.EventDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface LocationService {

    @POST("location/event/{id}")
    Call<Void> postLocation(@Path("id") Integer id, @Body Object object);

}
