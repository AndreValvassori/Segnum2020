package com.andrevalvassori.segnum2020.Singleton;

import android.content.Context;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.DTO.UserDTO;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataStorage {
    private static DataStorage instance = null;
    private Context context;

    private UserDTO usuario;

    protected DataStorage(){}

    public static DataStorage sharedInstance(){
        if(instance == null)
            instance = new DataStorage();

        return instance;
    }

    public void setContext(Context context) {

        this.context = context;
    }

    public int UserLogin(String login, String senha)
    {
        //TODO Implementar login!
        return 1;
    }

    public boolean LoadUser(int id)
    {
        final Call<UserDTO> userCall = new RetrofitInitialization().getUserService().getUser(1);
        userCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                usuario = response.body();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                usuario = null;
            }
        });

        return (usuario != null);
    }

    public void PostUser(UserDTO user)
    {
        final boolean resultado = false;
        Call<Object> objectCall = new RetrofitInitialization().getUserService().postRegister(user);

        objectCall.enqueue(new Callback<Object>() {

            @Override
            public void onResponse(Call<Object> call, Response<Object> response) {
                if(response.code() == 200 || response.code() == 201)
                {
                    Toast.makeText (context, "Registrado com sucesso!", Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Object> call, Throwable t) {
                Toast.makeText (context, "Falha ao registrar!", Toast.LENGTH_LONG).show();
            }
        });
    }
}
