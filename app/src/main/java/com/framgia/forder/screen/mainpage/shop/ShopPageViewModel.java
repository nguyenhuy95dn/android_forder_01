package com.framgia.forder.screen.mainpage.shop;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.data.model.Shop;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.utils.Constant.STATUS_OFF;
import static com.framgia.forder.utils.Constant.STATUS_ON;

/**
 * Created by ths on 21/06/2017.
 */

public class ShopPageViewModel extends BaseObservable implements ShopPageContract.ViewModel {

    private static final String TAG = ShopPageViewModel.class.getName();
    private static final String STATUS_OPEN = "OPEN";
    private static final String STATUS_CLOSE = "CLOSE";

    private ShopPageContract.Presenter mPresenter;
    private Shop mShop;
    private Navigator mNavigator;
    private String mStatusShop;

    public ShopPageViewModel(Shop shop, Navigator navigator) {
        mShop = shop;
        mNavigator = navigator;
        initValueTextStatus();
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

    @Bindable
    public String getShopName() {
        return mShop.getName();
    }

    public String getShopImage() {
        if (mShop.getCollectionAvatar() != null) {
            return mShop.getCollectionAvatar().getUrl();
        }
        return "";
    }

    @Bindable
    public String getStatusShop() {
        return mStatusShop;
    }

    public void setStatusShop(String statusShop) {
        mStatusShop = statusShop;
        notifyPropertyChanged(BR.statusShop);
    }

    private void initValueTextStatus() {
        switch (mShop.getStatusShop()) {
            case STATUS_ON:
                setStatusShop(STATUS_OPEN);
                break;
            case STATUS_OFF:
                setStatusShop(STATUS_CLOSE);
                break;
            default:
                break;
        }
    }

    public void onClickShopItem() {
        //Todo edit later
    }
}
