package com.example.duong.android_forder_01.ui.home.shop;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.data.model.User;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

public interface ShopContract {
    interface View extends BaseView<Presenter> {
        void initRecyclerView();
        void openShopDetail(Shop shop);
        void showAllShop(List<Shop> list);
        void showGetDataError();
    }

    interface Presenter extends BasePresenter {
        void openShopDetail(Shop shop);
        void getAllShop(int idDomain, User user);
    }
}
