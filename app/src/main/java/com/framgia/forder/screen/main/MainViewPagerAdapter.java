package com.framgia.forder.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by tri on 13/04/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_NUMBER = 5;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return FragmentContainer.newInstance(position);
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }
}
