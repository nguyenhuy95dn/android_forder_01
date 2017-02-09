package com.example.duong.android_forder_01.ui.home.product;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;

public class ProductItemActionHandler {
    private ProductContract.Presenter mListener;

    public ProductItemActionHandler(ProductContract.Presenter listener) {
        mListener = listener;
    }

    public void addShoppingCardClick(Product product) {
        mListener.addShoppingCard(product);
    }

    public void openShopDetailClick(Shop shop) {
        mListener.openShopDetail(shop);
    }

    public void itemClick(Product product) {
        mListener.openProductDetail(product);
    }
}
