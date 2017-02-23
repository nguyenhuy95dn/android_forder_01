package com.example.duong.android_forder_01.ui.notification;

import com.example.duong.android_forder_01.ui.BasePresenter;
import com.example.duong.android_forder_01.ui.BaseView;

/**
 * Created by tri on 23/02/2017.
 */
public interface NotificationContract {
    interface View extends BaseView<NotificationContract.Presenter> {
        void initRecyclerNotification();
    }

    interface Presenter extends BasePresenter {
    }
}
