package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.DTO.user.UserNewDTO;
import com.andrevalvassori.segnum2020.Singleton.DataStorage;
import com.andrevalvassori.segnum2020.retrofift.RetrofitInitialization;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

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

    private Timer timerVerifyLogin;

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

        timerVerifyLogin = new Timer(true);
        timerVerifyLogin.scheduleAtFixedRate(new TimerTask()
         {
             @Override
             public void run() {
                 runOnUiThread(new Runnable()
                 {
                     @Override
                     public void run()
                     {
                         if(DataStorage.sharedInstance().getUser() != null) {
                             finish();
                             timerVerifyLogin.cancel();
                             timerVerifyLogin.purge();
                         }
                     }
                 });
             }
         },
        0,
        1500
        );
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event)
    {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            BackButton();
        }
        return super.onKeyDown(keyCode, event);
    }

    @Override
    public void onBackPressed() {
        BackButton();
    }

    private void BackButton()
    {
        timerVerifyLogin.cancel();
        timerVerifyLogin.purge();
        super.onBackPressed();
        this.finish();
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
                        UserNewDTO newuser;
                        newuser = new UserNewDTO();
                        newuser.setName(etRegisterNome.getText().toString());
                        newuser.setEmail(etRegisterEmail.getText().toString());
                        newuser.setPassword(etRegisterSenha.getText().toString());
                        newuser.setBirthday(Calendar.getInstance().getTime());

                        DataStorage.sharedInstance().RegisterUser(newuser);

                    }
                }
            }
        }
    }



    public void btnCancelarOnClick(View view)
    {

    }
}
