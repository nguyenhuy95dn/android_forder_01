package com.example.duong.android_forder_01.ui.home.shop;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.source.DataSource;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;

import java.util.List;

public class ShopPresenter implements ShopContract.Presenter {
    private ShopContract.View mShopView;
    private DataSource mDataRepository;

    public ShopPresenter(@NonNull ShopContract.View shopView, DataSource dataRepository) {
        mShopView = shopView;
        mShopView.setPresenter(this);
        mDataRepository = dataRepository;
    }

    @Override
    public void start() {
        mShopView.start();
    }

    @Override
    public void openShopDetail(Shop shop) {
        if (shop == null) return;
        mShopView.openShopDetail(shop);
    }

    @Override
    public void getAllShop(int idDomain) {
        mDataRepository.getDatas(idDomain, new GetDataCallback<Shop>() {
            @Override
            public void onLoaded(List<Shop> datas) {
                mShopView.showAllShop(datas);
            }

            @Override
            public void onNotAvailable() {
                mShopView.showGetDataError();
            }
        });
    }
}
