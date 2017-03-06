package com.example.duong.android_forder_01.ui.home.shop;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.source.GetDataCallback;
import com.example.duong.android_forder_01.data.source.ShopDataSource;

import java.util.List;

public class ShopPresenter implements ShopContract.Presenter {
    private ShopContract.View mShopView;
    private ShopDataSource mDataRepository;

    public ShopPresenter(@NonNull ShopContract.View shopView, ShopDataSource dataRepository) {
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
        mDataRepository.getDataShop(idDomain, new GetDataCallback<Shop>() {
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
