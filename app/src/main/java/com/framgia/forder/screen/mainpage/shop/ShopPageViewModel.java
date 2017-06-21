package com.framgia.forder.screen.mainpage.shop;

import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.screen.shopDetail.ShopDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Created by ths on 21/06/2017.
 */

public class ShopPageViewModel extends BaseObservable implements ShopPageContract.ViewModel {

    private static final String TAG = ShopPageViewModel.class.getName();

    private ShopPageContract.Presenter mPresenter;
    private Shop mShop;
    private Navigator mNavigator;

    public ShopPageViewModel(Shop shop, Navigator navigator) {
        mShop = shop;
        mNavigator = navigator;
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
    public void setPresenter(ShopPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getShopName() {
        return mShop.getName();
    }

    public String getShopImage() {
        if (mShop.getCollectionAvatar() != null && mShop.getCollectionAvatar().getImage() != null) {
            return mShop.getCollectionAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickShopItem() {
        mNavigator.goNextChildFragment(R.id.layout_content, ShopDetailFragment.newInstance(mShop),
                true, Navigator.RIGHT_LEFT, "ShopDetailFragment");
    }
}
