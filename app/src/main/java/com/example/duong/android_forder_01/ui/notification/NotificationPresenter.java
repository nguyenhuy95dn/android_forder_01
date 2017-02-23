package com.example.duong.android_forder_01.ui.notification;

import android.support.annotation.NonNull;

/**
 * Created by tri on 23/02/2017.
 */
public class NotificationPresenter implements NotificationContract.Presenter {
    private NotificationContract.View mView;

    public NotificationPresenter(@NonNull NotificationContract.View notificationView) {
        mView = notificationView;
        mView.setPresenter(this);
    }

    @Override
    public void start() {
        mView.start();
    }
}
