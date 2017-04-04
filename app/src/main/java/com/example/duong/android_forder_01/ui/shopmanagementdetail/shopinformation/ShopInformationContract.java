package com.example.duong.android_forder_01.ui.shopmanagementdetail.shopinformation;

import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface ShopInformationContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void openOrderList(Shop shop);
        void openOrderHistory(Shop shop);
        void editOwner(Shop shop);
        void editInformation(Shop shop);
    }
}
