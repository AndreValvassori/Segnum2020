package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.DTO.UserDTO;

import com.andrevalvassori.segnum2020.Singleton.DataStorage;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    EditText etLogin;
    EditText etSenha;
    Button btLogin;
    Button btFacebook;
    TextView tvRegistro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        etLogin = findViewById(R.id.et_login_email);
        etSenha = findViewById(R.id.et_login_pass);

        btLogin = findViewById(R.id.btn_login_login);
        btFacebook = findViewById(R.id.btn_login_facebook);

        tvRegistro = findViewById(R.id.tv_login_cadastro);

        DataStorage.sharedInstance().setContext(this);
    }

    public void btnLoginOnClick(View view)
    {
        int userid = 0;
        if(!etLogin.getText().toString().equals("") && !etSenha.getText().toString().equals(""))
        {
            userid = DataStorage.sharedInstance().UserLogin(etLogin.getText().toString(),etSenha.getText().toString());
            if(userid != 0)
            {
                DataStorage.sharedInstance().LoadUser(userid);
                Intent intentMainActivity = new Intent(this, Main2Activity.class);
                this.startActivity(intentMainActivity);
            }
            else
            {
                Toast.makeText (LoginActivity.this, "Não foi possível Realizar login! Verifique Usuário e Senha!", Toast.LENGTH_LONG).show();
            }
        }
    }

    public void btnFacebookOnClick(View view){

    }

    public void btnRegisterOnClick(View view){
        Intent intentRegisterActivity = new Intent(this, RegisterActivity.class);
        this.startActivity(intentRegisterActivity);
    }

    void teste() {
        final Call<UserDTO> userCall = new RetrofitInitialization().getUserService().getUser(1);
        userCall.enqueue(new Callback<UserDTO>() {
            @Override
            public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                Toast.makeText (LoginActivity.this, response.body().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFailure(Call<UserDTO> call, Throwable t) {
                Toast.makeText (LoginActivity.this, "Fail: "+t.getMessage().toString(), Toast.LENGTH_LONG).show();
            }
        });

    }
}
