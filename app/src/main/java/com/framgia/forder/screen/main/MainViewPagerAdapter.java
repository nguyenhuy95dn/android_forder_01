package com.framgia.forder.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;

/**
 * Created by tri on 13/04/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_NUMBER = 5;

    private Fragment mCurrentFragment;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        return MainContainerFragment.newInstance(position);
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        mCurrentFragment = ((Fragment) object);
        super.setPrimaryItem(container, position, object);
    }

    public Fragment getCurrentFragment() {
        return mCurrentFragment;
    }
}
