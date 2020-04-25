package com.andrevalvassori.segnum2020.Controller.UserProfileFragments;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.andrevalvassori.segnum2020.Adapter.EventListAdapter;
import com.andrevalvassori.segnum2020.Controller.SendAlertActivity;
import com.andrevalvassori.segnum2020.DTO.user.UserDTO;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;

public class BaseProfileFragment extends Fragment {

    EditText etPerfilNome;
    EditText etPerfilEmail;
    Button btnDeslogar;

    View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_events,container,false);

        etPerfilNome = view.findViewById(R.id.et_profile_nome);
        etPerfilEmail = view.findViewById(R.id.et_profile_email);
        btnDeslogar = view.findViewById(R.id.btn_profile_deslogar);

        btnDeslogar.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v)
            {
                DataStore.sharedInstance().setUser(null);
                getActivity().finish();
            }
        });

        return view;
    }

}
