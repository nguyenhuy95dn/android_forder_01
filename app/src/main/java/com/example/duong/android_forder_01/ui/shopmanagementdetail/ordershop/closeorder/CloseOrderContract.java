package com.example.duong.android_forder_01.ui.shopmanagementdetail.ordershop.closeorder;

import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

/**
 * Created by tri on 07/03/2017.
 */
public interface CloseOrderContract {
    interface View extends BaseView<Presenter> {
    }

    interface Presenter extends BasePresenter {
        void backToCheckOrder();
        void done();
    }
}
