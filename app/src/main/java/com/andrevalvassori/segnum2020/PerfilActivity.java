package com.andrevalvassori.segnum2020;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.Singleton.DataStorage;

public class PerfilActivity extends AppCompatActivity {

    EditText etPerfilNome;
    EditText etPerfilEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        etPerfilNome = findViewById(R.id.et_perfil_name);
        etPerfilEmail = findViewById(R.id.et_perfil_email);

        DataStorage.sharedInstance().setContext(this);

        UserDTO usuario = DataStorage.sharedInstance().getUser();

        etPerfilNome.setText(usuario.getName());
        etPerfilEmail.setText(usuario.getEmail());

    }

    public void btnDeslogarOnClick(View view)
    {
        DataStorage.sharedInstance().setUser(null);
        finish();
    }
}
