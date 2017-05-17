package com.framgia.forder.screen.shopmanagement;

import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.shopinfo.ShopInformationPageContainerFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Created by levutantuan on 5/3/17.
 */

public class ItemShopManagementViewModel {
    private final Navigator mNavigator;
    private final ShopManagement mShopManagement;
    private static final String TAG = "ShopManagemantFragment";

    public ItemShopManagementViewModel(Navigator navigator, ShopManagement shopManagements) {
        mNavigator = navigator;
        mShopManagement = shopManagements;
    }

    public String getShopImage() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCollectionAvatar() != null
                && mShopManagement.getShop().getCollectionAvatar().getImage() != null) {
            return mShopManagement.getShop().getCollectionAvatar().getImage().getUrl();
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

    public void onClickShopInformation() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                ShopInformationPageContainerFragment.newInstance(this.mShopManagement), true,
                Navigator.BOTTOM_UP, TAG);
    }
}
