package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.data.model.ShopInfo;

/**
 * Created by levutantuan on 5/8/17.
 */

public class ItemShopInfoViewModel {
    private final ShopInfo mShopInfo;

    public ItemShopInfoViewModel(ShopInfo shopInfo) {
        mShopInfo = shopInfo;
    }

    public int getDomainId() {
        if (mShopInfo != null) {
            return mShopInfo.getDomainId();
        }
        return 0;
    }

    public String getDomainName() {
        if (mShopInfo != null) {
            return mShopInfo.getDomainName();
        }
        return "";
    }

    public int getNumberUser() {
        if (mShopInfo != null) {
            return mShopInfo.getNumberUser();
        }
        return 0;
    }

    public int getNumberShop() {
        if (mShopInfo != null) {
            return mShopInfo.getNumberShop();
        }
        return 0;
    }

    public int getNumberProduct() {
        if (mShopInfo != null) {
            return mShopInfo.getNumberProduct();
        }
        return 0;
    }
}
