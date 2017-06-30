package com.framgia.forder.screen.shopDetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.Shop;

/**
 * Exposes the data to be used in the DetailShop screen.
 */

public class ShopDetailViewModel extends BaseObservable implements ShopDetailContract.ViewModel {

    private ShopDetailContract.Presenter mPresenter;
    private Shop mShop;

    public ShopDetailViewModel(Shop shop) {
        mShop = shop;
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
    public void setPresenter(ShopDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getName() {
        return mShop.getName();
    }

    @Bindable
    public String getDescription() {
        return mShop.getDescription();
    }

    @Bindable
    public float getAverageRating() {
        return mShop.getAverageRating();
    }

    @Bindable
    public String getShopAvatar() {
        if (mShop != null
                && mShop.getCollectionAvatar() != null
                && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getShopImage() {
        if (mShop != null
                && mShop.getCoverImage() != null
                && mShop.getCoverImage().getImage() != null) {
            return mShop.getCoverImage().getImage().getUrl();
        }
        return "";
    }

    @Bindable
    public String getStatus() {
        return mShop.getStatus();
    }

    @Bindable
    public String getShopOwnerEmail() {
        if (mShop.getUser() != null) {
            return mShop.getUser().getEmail();
        }
        return "";
    }

    @Bindable
    public String getShopOwnerName() {
        if (mShop.getUser() != null) {
            return mShop.getUser().getName();
        }
        return "";
    }
}
