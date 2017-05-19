package com.framgia.forder.screen.shopinfo;

import android.content.Context;
import android.support.annotation.IntDef;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.shopinfo.productshopinfo.ProductShopInfoFragment;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by levutantuan on 5/15/17.
 */

public class ShopInformationPageAdapter extends FragmentPagerAdapter {

    private static final int TAB_NUMBER = 2;
    private final Context mContext;
    private final List<Fragment> mFragments;
    private final ShopManagement mShopManagement;

    public ShopInformationPageAdapter(Context context, FragmentManager fragmentManager,
            ShopManagement shopManagement) {
        super(fragmentManager);
        mContext = context;
        mFragments = new ArrayList<>();
        mShopManagement = shopManagement;
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment;
        switch (position) {
            case ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_SHOP:
                fragment = ShopinfoFragment.newInstance(mShopManagement);
                mFragments.add(ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_SHOP,
                        fragment);
                return fragment;
            case ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_PRODUCT:
                fragment = ProductShopInfoFragment.newInstance(mShopManagement);
                mFragments.add(ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_PRODUCT,
                        fragment);
                return fragment;
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
            case ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_SHOP:
                return mContext.getString(R.string.shop_information);
            case ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_PRODUCT:
                return mContext.getString(R.string.products_shop);
            default:
                return null;
        }
    }

    public Fragment getFragment(
            @ShopInformationPageAdapter.ShopInformationPageResultsTab int position) {
        return mFragments.get(position);
    }

    @IntDef({
            ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_SHOP,
            ShopInformationPageAdapter.ShopInformationPageResultsTab.TAB_PRODUCT
    })
    public @interface ShopInformationPageResultsTab {
        int TAB_SHOP = 0;
        int TAB_PRODUCT = 1;
    }
}
