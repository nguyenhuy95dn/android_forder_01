package com.example.duong.android_forder_01.ui.domain.detaildomain.shopdomain;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.source.ShopDataSource;

/**
 * Created by Vinh on 07/03/2017.
 */
public class ShopDomainPresenter implements ShopDomainContract.Presenter {
    private ShopDomainContract.View mView;
    private ShopDataSource mShopDataRepository;

    public ShopDomainPresenter(@NonNull ShopDomainContract.View view,
                               ShopDataSource shopDataRepository) {
        mView = view;
        view.setPresenter(this);
        mShopDataRepository = shopDataRepository;
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
