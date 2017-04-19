package com.framgia.forder.screen.searchpage;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.forder.R;
import com.framgia.forder.screen.searchproduct.ProductSearchResultFragment;
import com.framgia.forder.screen.searchshop.ShopSearchResultFragment;

public class SearchContainerAdapter extends FragmentPagerAdapter {
    private static final int TAB_NUMBER = 2;
    private static final int TAB_PRODUCT = 0;
    private static final int TAB_SHOP = 1;

    private Context mContext;

    public SearchContainerAdapter(FragmentManager fm, Context context) {
        super(fm);
        mContext = context;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case TAB_PRODUCT:
                return ProductSearchResultFragment.newInstance();
            case TAB_SHOP:
                return ShopSearchResultFragment.newInstance();
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
            case TAB_PRODUCT:
                return mContext.getString(R.string.product);
            case TAB_SHOP:
                return mContext.getString(R.string.shop);
            default:
                return null;
        }
    }
}
