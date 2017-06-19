package com.framgia.forder.screen.mainpage.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by ths on 19/06/2017.
 */

public class ShopPageAdapter extends FragmentPagerAdapter {
    private static final int TAB_NUMBER = 6;

    public ShopPageAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        //TODO add slide view pager
        return null;
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }
}
