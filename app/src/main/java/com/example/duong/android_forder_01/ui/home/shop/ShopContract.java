package com.example.duong.android_forder_01.ui.home.shop;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ShopContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
        void openShopDetail(Shop shop);
    }

    interface Presenter extends BasePresenter {
        void openShopDetail(Shop shop);
    }
}
