package com.framgia.forder.screen.main;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tri on 13/04/2017.
 */

public class MainViewPagerAdapter extends FragmentStatePagerAdapter {
    private static final int TAB_NUMBER = 5;

    private Fragment mCurrentFragment;
    private List<Fragment> mFragments;

    public MainViewPagerAdapter(FragmentManager fm) {
        super(fm);
        mFragments=new ArrayList<>();
        for (int i = 0; i < TAB_NUMBER; i++) {
            mFragments.add(null);
        }
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = MainContainerFragment.newInstance(position);
        mFragments.set(position, fragment);
        return fragment;
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

    public Fragment getFragment(@MainViewModel.Tab int position) {
        try {
            return mFragments.get(position);
        } catch (IndexOutOfBoundsException e) {
            return null;
        }
    }
}
