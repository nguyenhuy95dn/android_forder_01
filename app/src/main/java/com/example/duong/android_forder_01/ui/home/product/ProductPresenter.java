package com.example.duong.android_forder_01.ui.home.product;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;

public class ProductPresenter implements ProductContract.Presenter {
    private ProductContract.View mProductView;

    public ProductPresenter(@NonNull ProductContract.View productView) {
        mProductView = productView;
        mProductView.setPresenter(this);
    }

    @Override
    public void start() {
        mProductView.start();
    }

    @Override
    public void openShopDetail(Shop shop) {
    }

    @Override
    public void openProductDetail(Product product) {
    }

    @Override
    public void addShoppingCard(Product product) {
    }
}
