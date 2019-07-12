package com.peenats.ayana.colorlens.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.peenats.ayana.colorlens.Fragment.ColorsFragment;
import com.peenats.ayana.colorlens.Fragment.DoodleFragment;
import com.peenats.ayana.colorlens.Fragment.FramesFragment;

// FragmentPagerAdapter: it's used when you have a limited number of tabs that want to swipe through
public class TabPageAdapter extends FragmentPagerAdapter {
    private int numOfTabs;

    public TabPageAdapter(FragmentManager fm, int numOfTabs) {

        super(fm);
        this.numOfTabs = numOfTabs;
    }

    @Override
    public Fragment getItem(int i) {
        switch (i) {
            case 0:
                return new ColorsFragment();
            case 1:
                return new FramesFragment();
            case 2:
                return new DoodleFragment();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}
