package com.example.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.segnum2020.retrofift.RetrofitInitialization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        Call<Object> objectCall = new RetrofitInitialization().getUserService().postRegister(new Object());

        objectCall.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {

            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {

            }
        });


    }
}
