package com.example.baitap.Adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;

public class MainViewPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Fragment> arrayFragment = new ArrayList<>();
    private ArrayList<String> arrayTitle = new ArrayList<>();
    public MainViewPagerAdapter(FragmentManager fragmentManager){
        super(fragmentManager);
    }

    @Override
    // trả về fragment tại vị trí position
    public Fragment getItem(int position) {
        return arrayFragment.get(position);
    }

    @Override
    // trả về số lượng fragment hiện có
    public int getCount() {
        return arrayFragment.size();
    }


    public void addFragment(Fragment fragment, String title){
        arrayFragment.add(fragment);
        arrayTitle.add(title);
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return arrayTitle.get(position);
    }
}
