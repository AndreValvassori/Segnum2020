package com.andrevalvassori.segnum2020.Controller.UserProfileFragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

public class MyAddressFragment extends Fragment {

    EditText etHomeAddress;
    EditText etWorkAddress;
    EditText etSchoolAddress;
    Button btnSave;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_my_address,container,false);

        etHomeAddress = view.findViewById(R.id.et_my_address_home);
        etWorkAddress = view.findViewById(R.id.et_my_address_work);
        etSchoolAddress = view.findViewById(R.id.et_my_address_school);
        btnSave = view.findViewById(R.id.btn_salvar_enderecos);
        btnSave.setOnClickListener(v -> {
        });
//
//        UserDTO usuario = DataStore.sharedInstance().getUser();
//        etHomeAddress.setText(usuario.getName());
//        etWorkAddress.setText(usuario.getEmail());
//        etSchoolAddress.setText(usuario.getName());

        return view;
    }

}
