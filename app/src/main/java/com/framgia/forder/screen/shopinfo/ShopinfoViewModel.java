package com.framgia.forder.screen.shopinfo;

import com.framgia.forder.R;
import com.framgia.forder.data.model.ShopInfo;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.screen.shopupdate.ShopUpdateFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Shopinfo screen.
 */

public class ShopinfoViewModel implements ShopinfoContract.ViewModel {

    private static final String TAG = "ShopUpdateFragment";
    private final Navigator mNavigator;
    private ShopinfoContract.Presenter mPresenter;
    private final ShopManagement mShopManagement;
    private final ShopInfo mShopInfo;

    ShopinfoViewModel(Navigator navigator, ShopManagement shopManagement) {
        mNavigator = navigator;
        mShopManagement = shopManagement;
        mShopInfo = mShopManagement.getShopInfos().get(1);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopinfoContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getShopImage() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCoverImage() != null
                && mShopManagement.getShop().getCoverImage().getImage() != null) {
            return mShopManagement.getShop().getCoverImage().getImage().getUrl();
        }
        return "";
    }

    public String getShopName() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getName();
        }
        return "";
    }

    public float getRating() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getAverageRating();
        }
        return 0;
    }

    public String getNumberProduct() {
        if (mShopInfo != null) {
            return String.valueOf(mShopInfo.getNumberProduct());
        }
        return "";
    }

    public String getTimeOpenShop() {
        if (mShopManagement.getShop() != null) {
            return mShopManagement.getShop().getTimeOpenShop();
        }
        return "";
    }

    public String getShopAvatar() {
        if (mShopManagement.getShop() != null
                && mShopManagement.getShop().getCollectionAvatar() != null
                && mShopManagement.getShop().getCollectionAvatar().getImage() != null) {
            return mShopManagement.getShop().getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickEditShop() {
        mNavigator.goNextChildFragment(R.id.layout_content, ShopUpdateFragment.newInstance(), true,
                Navigator.BOTTOM_UP, TAG);
    }
}
