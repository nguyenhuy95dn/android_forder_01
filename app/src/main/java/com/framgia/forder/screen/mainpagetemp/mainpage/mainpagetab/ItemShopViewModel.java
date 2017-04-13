package com.framgia.forder.screen.mainpagetemp.mainpage.mainpagetab;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemShopViewModel extends BaseObservable {
    private Shop mShop;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Shop> mItemClickListener;

    public ItemShopViewModel(Shop shop,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Shop> listener) {
        mShop = shop;
        mItemClickListener = listener;
    }
}
