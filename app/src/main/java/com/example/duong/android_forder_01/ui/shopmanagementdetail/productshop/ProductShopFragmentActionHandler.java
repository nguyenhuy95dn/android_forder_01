package com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop;

import com.example.duong.android_forder_01.data.model.Product;

/**
 * Created by tri on 07/03/2017.
 */
public class ProductShopFragmentActionHandler {
    private ProductShopFragmentContract.Presenter mListener;

    public ProductShopFragmentActionHandler(ProductShopFragmentContract.Presenter listener) {
        mListener = listener;
    }

    public void itemClick(Product product) {
        if (mListener == null) return;
        mListener.itemClick(product);
    }

    public void deleteProduct(Product product) {
        if (mListener == null) return;
        mListener.deleteProduct(product);
    }

    public void updateProduct(Product product) {
        if (mListener == null) return;
        mListener.updateProduct(product);
    }

    public void addProduct(Product product) {
        if (mListener == null) return;
        mListener.addProduct(product);
    }
}
