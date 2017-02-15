package com.example.duong.android_forder_01.ui.productdetail;

import com.example.duong.android_forder_01.data.model.Product;

public class ProductDetailActionHandler {
    private ProductDetailContract.Presenter mListener;

    public ProductDetailActionHandler(ProductDetailContract.Presenter listener) {
        mListener = listener;
    }

    public void addShoppingCard(Product product) {
        if (mListener == null) return;
        mListener.addShoppingCard(product);
    }

    public void order(Product product) {
        if (mListener == null) return;
        mListener.order(product);
    }
}
