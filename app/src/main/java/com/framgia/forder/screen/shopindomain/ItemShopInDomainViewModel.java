package com.framgia.forder.screen.shopindomain;

import com.framgia.forder.data.model.ShopInDomain;

import static com.framgia.forder.utils.Constant.FORMAT_PRODUCT;

/**
 * Created by Age on 6/14/2017.
 */

public class ItemShopInDomainViewModel {

    private final ShopInDomain mShop;

    ItemShopInDomainViewModel(ShopInDomain shop) {
        mShop = shop;
    }

    public String getShopName() {
        return mShop.getName();
    }

    public String getTotalProduct() {
        return mShop.getTotalProduct() + FORMAT_PRODUCT;
    }

    public String getOwnerName() {
        return mShop.getOwnerName();
    }

    public String getImageShop() {
        return mShop.getAvatar();
    }

    public String getStatus() {
        return mShop.getStatus();
    }
}
