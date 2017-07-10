package com.framgia.forder.screen.shopindomain;

import com.framgia.forder.data.model.ShopInDomain;

import static com.framgia.forder.utils.Constant.FORMAT_PRODUCT;

/**
 * Created by Age on 6/14/2017.
 */

public class ItemShopInDomainViewModel {

    private final ShopInDomain mShop;
    private final ShopInDomainListener mShopInDomainListener;

    ItemShopInDomainViewModel(ShopInDomain shop, ShopInDomainListener shopInDomainListener) {
        mShop = shop;
        mShopInDomainListener = shopInDomainListener;
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
        if (mShop.getAvatar() != null && mShop.getAvatar().getImage() != null) {
            return mShop.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getStatus() {
        return String.valueOf(mShop.getStatus());
    }

    public int getStatusColor() {
        return mShop.getStatusColor();
    }

    public void onClickSeeAllManager() {
        mShopInDomainListener.onClickSeeAllManager(mShop.getOwnerShops());
    }

    public void onClickDeleteShop() {
        mShopInDomainListener.onClickDeleteShop(mShop);
    }

    public boolean isOwner() {
        return mShop.isOwner();
    }
}
