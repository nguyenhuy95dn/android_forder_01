package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder.CheckOrderFragment;

/**
 * Created by Duong on 3/10/2017.
 */
public class OrderOfShopViewPagerAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 2;
    private final int CHECK_ORDER_POSITION = 0;
    private final int CLOSE_ORDER_POSITION = 1;
    private Context mContext;
    private Shop mShop;

    public OrderOfShopViewPagerAdapter(FragmentManager fm, Context context,
                                       Shop shop) {
        super(fm);
        mContext = context;
        mShop = shop;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case CHECK_ORDER_POSITION:
                return CheckOrderFragment.newInstance(mShop);
            case CLOSE_ORDER_POSITION:
                return CheckOrderFragment.newInstance(mShop);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return TAB_NUMBER;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case CHECK_ORDER_POSITION:
                return mContext.getString(R.string.title_check_orders);
            case CLOSE_ORDER_POSITION:
                return mContext.getString(R.string.title_close_orders);
            default:
                return null;
        }
    }
}

