package com.framgia.forder.screen.mainpage.shop;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.framgia.forder.data.model.Shop;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by ths on 19/06/2017.
 */

public class ShopPageAdapter extends FragmentStatePagerAdapter {

    private List<Shop> mShopList;

    public ShopPageAdapter(FragmentManager fm) {
        super(fm);
        mShopList = new ArrayList<>();
    }

    @Override
    public Fragment getItem(int position) {
        if (mShopList.size() == 0) {
            return null;
        }
        return ShopPageFragment.newInstance(mShopList.get(position));
    }

    @Override
    public int getCount() {
        return mShopList.size();
    }

    public void updateShop(List<Shop> shopList) {
        mShopList.addAll(shopList);
        notifyDataSetChanged();
    }
}
