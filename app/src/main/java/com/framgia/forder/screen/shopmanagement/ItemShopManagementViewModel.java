package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ItemShopManagementViewModel {
    private final ShopManagement mShopManagement;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object>
            mItemClickListener;

    public ItemShopManagementViewModel(ShopManagement shopManagements,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> listener) {
        mShopManagement = shopManagements;
        mItemClickListener = listener;
    }

    public String getShopImage() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCoverImage() != null
                && mShopManagement.getShop().getCoverImage().getUrl() != null) {
            return mShopManagement.getShop().getCoverImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getName();
        }
        return "";
    }

    public String getDescription() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getDescription();
        }
        return "";
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mShopManagement);
    }
}
