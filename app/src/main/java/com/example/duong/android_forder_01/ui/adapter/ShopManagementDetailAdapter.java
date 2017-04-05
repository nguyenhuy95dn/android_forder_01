package com.example.duong.android_forder_01.ui.adapter;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop.ProductShopFragment;
import com.example.duong.android_forder_01.ui.shopmanagementdetail.shopinformation.ShopInformationFragment;

/**
 * Created by tri on 06/03/2017.
 */
public class ShopManagementDetailAdapter extends FragmentPagerAdapter {
    private final int TAB_NUMBER = 2;
    private final int SHOP_INFOR_POSITION = 0;
    private final int SHOP_PRODUCT_POSITION = 1;
    private Context mContext;
    private ShopManagement mShopManagement;

    public ShopManagementDetailAdapter(FragmentManager fm, Context context,
                                       ShopManagement shopManagement) {
        super(fm);
        mContext = context;
        mShopManagement = shopManagement;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case SHOP_INFOR_POSITION:
                return ShopInformationFragment.newInstance(mShopManagement.getShop());
            case SHOP_PRODUCT_POSITION:
                return ProductShopFragment.newInstance(mShopManagement.getShop());
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
