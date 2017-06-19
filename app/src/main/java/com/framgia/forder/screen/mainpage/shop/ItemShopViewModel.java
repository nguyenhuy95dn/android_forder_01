package com.framgia.forder.screen.mainpage.shop;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by Duong on 4/13/2017.
 */

public class ItemShopViewModel extends BaseObservable {
    private final Shop mShop;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemShopViewModel(Shop shop,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mShop = shop;
        mItemClickListener = listener;
    }

    public String getShopImage() {
        if (mShop.getCollectionAvatar() != null && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShop != null) {
            return mShop.getName();
        }
        return "";
    }

    public String getDescription() {
        if (mShop != null) {
            return mShop.getDescription();
        }
        return "";
    }

    public float getRating() {
        return mShop.getAverageRating();
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mShop);
    }

    public String getOwnerName() {
        if (mShop != null) {
            return mShop.getNameOwner();
        }
        return "";
    }

    public String getOwnerEmail() {
        if (mShop != null) {
            return mShop.getEmailOwner();
        }
        return "";
    }

    public String getStatus() {
        if (mShop != null) {
            return mShop.getStatus();
        }
        return "";
    }
}
