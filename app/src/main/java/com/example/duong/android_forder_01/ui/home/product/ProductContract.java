package com.example.duong.android_forder_01.ui.home.product;

import com.example.duong.android_forder_01.data.model.Product;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ProductContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
    }

    interface Presenter extends BasePresenter {
        void openShopDetail(Shop shop);
        void openProductDetail(Product product);
        void addShoppingCard(Product product);
    }
}
