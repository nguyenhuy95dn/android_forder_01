package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.ui.home.product.ProductFragment;
import com.example.duong.android_forder_01.ui.home.shop.ShopFragment;

public class ViewPagerAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 2;
    private final int PRODUCT_POSITION = 0;
    private final int SHOP_POSITION = 1;
    private Context mContext;

    public ViewPagerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case PRODUCT_POSITION:
                return new ProductFragment();
            case SHOP_POSITION:
                return new ShopFragment();
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
            case PRODUCT_POSITION:
                return mContext.getString(R.string.title_products);
            case SHOP_POSITION:
                return mContext.getString(R.string.title_shops);
            default:
                return null;
        }
    }
}
