package com.andrevalvassori.segnum2020.Controller;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TabHost;

import com.andrevalvassori.segnum2020.Adapter.SectionsPageAdapter;
import com.andrevalvassori.segnum2020.Controller.UserProfileFragments.BaseProfileFragment;
import com.andrevalvassori.segnum2020.Controller.UserProfileFragments.MyAddressFragment;
import com.andrevalvassori.segnum2020.R;
import com.andrevalvassori.segnum2020.Singleton.DataStore;
import com.google.android.material.tabs.TabLayout;

public class PerfilActivity extends AppCompatActivity {

    private static final int MENU_ITEM1 = R.id.mainMenu_Item1;

    TabHost tabHostWindow = null;

    private static final String TAG = "MainActivity";

    private SectionsPageAdapter mSectionsPageAdapter;

    private ViewPager mViewPager;

    BaseProfileFragment baseProfileFragment;
    MyAddressFragment myAddressFragment;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        DataStore.sharedInstance().setContext(this);
        DataStore.sharedInstance().loadAllEvents();

        mSectionsPageAdapter = new SectionsPageAdapter(getSupportFragmentManager());

        mViewPager = findViewById(R.id.containerPerfil);
        setupViewPager(mViewPager);

        TabLayout tabLayout = findViewById(R.id.tabsPerfil);
        tabLayout.setupWithViewPager(mViewPager);

        FragmentManager manager = getSupportFragmentManager();
        Intent intent = getIntent();

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null)
            actionBar.setTitle("Meu Perfil - Segnum");

    }

    private void setupViewPager(ViewPager viewPager) {
        SectionsPageAdapter adapter = new SectionsPageAdapter(getSupportFragmentManager());

        baseProfileFragment = new BaseProfileFragment();
        myAddressFragment = new MyAddressFragment();

        adapter.addFragment(baseProfileFragment, "Informações Basicas");
        adapter.addFragment(myAddressFragment, "Meus Endereços");

        viewPager.setAdapter(adapter);
    }
}
