package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.checkorder;

import com.example.duong.android_forder_01.data.model.OrderDetail;
import com.example.duong.android_forder_01.data.model.Orders;
import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

import java.util.List;

/**
 * Created by tri on 07/03/2017.
 */
public interface CheckOrderContract {
    interface View extends BaseView<Presenter> {
        void shopListOrder(List<Orders> orders);
        void getListOrderError();
        void updateAdapter();
    }

    interface Presenter extends BasePresenter {
        void loadOrder(int shopId);
        void checkAll();
        void checkOne(OrderDetail orderDetail);
        void acceptAll();
        void acceptOne(OrderDetail orderDetail);
        void rejectAll();
        void rejectOne(OrderDetail orderDetail);
        void continueCheckOrder();
    }
}
