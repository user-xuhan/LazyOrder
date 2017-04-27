package com.example.xuhan.lazyorder.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by xuhan on 2017/3/30.
 */

public class ShopMainViewPageAdapter extends FragmentPagerAdapter {

    FragmentManager fm;
    List<Fragment> fragmentList = new ArrayList<>();

    public ShopMainViewPageAdapter(FragmentManager fm) {
        super(fm);
    }

    public ShopMainViewPageAdapter(FragmentManager fm, List<Fragment> fragmentList) {
        super(fm);
        this.fm = fm;
        this.fragmentList = fragmentList;
    }

    @Override
    public Fragment getItem(int position) {
        //Fragment fragment = fragmentList.get(position);
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }
}
