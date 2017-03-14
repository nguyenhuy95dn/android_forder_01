package com.example.duong.android_forder_01.ui.yourorder;

import com.example.duong.android_forder_01.data.model.Orders;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

public interface YourOrderContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void cancelOrder(Orders orders);
        void filter();
    }
}
