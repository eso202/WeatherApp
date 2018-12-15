package com.example.laptophome.weatherapp.Adapter;


import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class Fragment_Adapter extends FragmentPagerAdapter {
    private final List<Fragment> fragmentList=new ArrayList<>();
    private final List<String> fragmenttitle=new ArrayList<>();

    public Fragment_Adapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        return fragmentList.get(i);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addfragment(Fragment fragment,String title)
    {
        fragmentList.add(fragment);
        fragmenttitle.add(title);

    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return fragmenttitle.get(position);
    }
}
