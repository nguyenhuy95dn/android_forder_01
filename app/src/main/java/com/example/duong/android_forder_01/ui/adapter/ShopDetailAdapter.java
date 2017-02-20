package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.ui.home.product.ProductFragment;
import com.example.duong.android_forder_01.ui.shopdetail.shopinformation.ShopInformationFragment;

public class ShopDetailAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 2;
    private final int SHOP_INFOR_POSITION = 0;
    private final int SHOP_PRODUCT_POSITION = 1;
    private Context mContext;

    public ShopDetailAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SHOP_INFOR_POSITION:
                return ShopInformationFragment.newInstance();
            case SHOP_PRODUCT_POSITION:
                return ProductFragment.newInstance();
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
            case SHOP_INFOR_POSITION:
                return mContext.getString(R.string.title_shop_information);
            case SHOP_PRODUCT_POSITION:
                return mContext.getString(R.string.title_products);
            default:
                return null;
        }
    }
}

