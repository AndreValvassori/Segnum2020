package com.andrevalvassori.segnum2020.Adapter;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

public class ScreenSlidePagerAdapter extends FragmentStatePagerAdapter {
    private final List<Fragment> fragments;
    private final Context context;
    private boolean enabled;

    public ScreenSlidePagerAdapter(final Context context, final FragmentManager fragmentManager,
                                   final List<Fragment> fragments) {
        super(fragmentManager);
        this.context = context;
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(final int position) {
        return fragments.get(position);
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

}
