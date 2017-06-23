package com.framgia.forder.screen.shopinfo.listdomain;

import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.ShopInfo;
import com.framgia.forder.data.model.ShopManagement;

import static com.framgia.forder.utils.Constant.DEFAULT_VALUE;

/**
 * Created by levutantuan on 6/23/17.
 */

public class ItemListDomainViewModel {

    private final ShopManagement mShopManagement;
    private final ShopInfo mShopInfo;
    private final Domain mDomain;

    ItemListDomainViewModel(ShopManagement shopManagement) {
        mShopManagement = shopManagement;
        mDomain = mShopManagement.getShopDomains().get(DEFAULT_VALUE);
        mShopInfo = mShopManagement.getShopInfos().get(DEFAULT_VALUE);
    }

    public String getDomainName() {
        if (mShopManagement.getShopInfos() != null) {
            return mShopInfo.getDomainName();
        }
        return "";
    }

    public String getNumberUser() {
        return String.valueOf(mShopInfo.getNumberUser());
    }

    public String getNumberShop() {
        return String.valueOf(mShopInfo.getNumberShop());
    }

    public String getNumberProduct() {
        return String.valueOf(mShopInfo.getNumberProduct());
    }

    public String getStatus() {
        return String.valueOf(mDomain.getStatus());
    }
}
