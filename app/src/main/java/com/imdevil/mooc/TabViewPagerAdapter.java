package com.imdevil.mooc;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imdevil on 2017/1/25 0025.
 */
public class TabViewPagerAdapter extends FragmentPagerAdapter{

    private List<Fragment> list = new ArrayList<>();
    private List<String> theme = new ArrayList<>();

    public TabViewPagerAdapter(FragmentManager fm, List<Fragment> list,List<String> theme){
        super(fm);
        this.list = list;
        this.theme = theme;
    }

    @Override
    public Fragment getItem(int position) {
        return list.get(position);
    }

    @Override
    public int getCount() {
        return theme.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return theme.get(position);
    }
}
