package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.DTO.UserDTO;
import com.andrevalvassori.segnum2020.Singleton.DataStorage;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;

import java.util.Calendar;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static java.lang.System.in;

public class RegisterActivity extends AppCompatActivity {

    EditText etRegisterNome;
    EditText etRegisterEmail;
    EditText etRegisterSenha;
    EditText etRegisterTelefone;
    DatePicker dpNascimento;
    Button btRegister;
    Button btCancelar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etRegisterNome = findViewById(R.id.et_register_name);
        etRegisterEmail = findViewById(R.id.et_register_email);
        etRegisterSenha = findViewById(R.id.et_register_pass);
        etRegisterTelefone = findViewById(R.id.et_register_phone);
        dpNascimento = findViewById(R.id.dp_register_birthday);
        btRegister = findViewById(R.id.btn_register_register);
        btCancelar = findViewById(R.id.btn_register_cancel);

        DataStorage.sharedInstance().setContext(this);
    }

    public void btnRegisterOnClick(View view)
    {
        if(!etRegisterNome.getText().toString().equals(""))
        {
            if(!etRegisterEmail.getText().toString().equals(""))
            {
                if(!etRegisterSenha.getText().toString().equals(""))
                {
                    if(!etRegisterTelefone.getText().toString().equals(""))
                    {
                        UserDTO newuser;
                        newuser = new UserDTO();
                        newuser.setName(etRegisterNome.getText().toString());
                        newuser.setEmail(etRegisterEmail.getText().toString());
                        newuser.setPassword(etRegisterSenha.getText().toString());
                        newuser.setBirthday(Calendar.getInstance().getTime());

                        DataStorage.sharedInstance().PostUser(newuser);

                    }
                }
            }
        }
    }



    public void btnCancelarOnClick(View view)
    {

    }
}
