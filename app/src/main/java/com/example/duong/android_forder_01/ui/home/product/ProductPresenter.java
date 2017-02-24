package com.example.duong.android_forder_01.ui.home.product;

import android.support.annotation.NonNull;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.source.DataSource;
import com.example.duong.android_forder_01.data.model.source.GetDataCallback;

import java.util.List;

public class ProductPresenter implements ProductContract.Presenter {
    private ProductContract.View mProductView;
    private DataSource mDataRepository;

    public ProductPresenter(@NonNull ProductContract.View productView,
                            DataSource dataRepository) {
        mProductView = productView;
        mProductView.setPresenter(this);
        mDataRepository = dataRepository;
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
        mProductView.showProductDetail(product);
    }

    @Override
    public void addShoppingCard(Product product) {
    }

    @Override
    public void getAllProduct(int idDOmain) {
        mDataRepository.getDatas(idDOmain, new GetDataCallback<Product>() {
            @Override
            public void onLoaded(List<Product> datas) {
                mProductView.showAllProduct(datas);
            }

            @Override
            public void onNotAvailable() {
                mProductView.showGetDataError();
            }
        });
    }
}
