package com.example.duong.android_forder_01.ui.shopmanagementdetail.productshop;

import com.example.duong.android_forder_01.data.model.Product;

/**
 * Created by tri on 07/03/2017.
 */
public class ProductShopFragmentPresenter implements ProductShopFragmentContract.Presenter {
    private ProductShopFragmentContract.View mView;

    public ProductShopFragmentPresenter(ProductShopFragmentContract.View productView) {
        mView = productView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }

    @Override
    public void itemClick(Product product) {
        mView.showDetailShop(product);
    }

    @Override
    public void deleteProduct(Product product) {
        // TODO delete product
    }

    @Override
    public void updateProduct(Product product) {
        mView.showUiUpdateProduct(product);
    }

    @Override
    public void addProduct(Product product) {
        mView.showUiAddProduct(product);
    }
}
