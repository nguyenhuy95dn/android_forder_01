package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopManagement;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ItemShopManagementViewModel {
    private final ShopManagement mShopManagement;

    public ItemShopManagementViewModel(ShopManagement shopManagements) {
        mShopManagement = shopManagements;
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
}
