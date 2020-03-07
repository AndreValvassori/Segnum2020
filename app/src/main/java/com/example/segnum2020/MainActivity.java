package com.example.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.example.segnum2020.DTO.UserDTO;
import com.example.segnum2020.retrofift.RetrofitInitialization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        teste();
    }

    void teste() {
        final Call<UserDTO> userCall = new RetrofitInitialization().getUserService().getUser(1);
        userCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Toast.makeText (MainActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText (MainActivity.this, "Fail: "+t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
