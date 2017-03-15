package com.example.duong.android_forder_01.ui.domain.detaildomain.shopdomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.source.DataSource;

/**
 * Created by Vinh on 07/03/2017.
 */
public class ShopDomainPresenter implements ShopDomainContract.Presenter {
    private ShopDomainContract.View mView;
    private DataSource mShopDataSource;

    public ShopDomainPresenter(@NonNull ShopDomainContract.View view,
                               DataSource shopDataSource) {
        mView = view;
        view.setPresenter(this);
        mShopDataSource = shopDataSource;
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void getAllShopDomain(int idDomain) {
        //TODO: get shop in domain
    }
}
