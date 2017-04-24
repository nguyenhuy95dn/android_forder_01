package com.framgia.forder.screen.notification;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPagePresenter implements NotificationPageContract.Presenter {
    private static final String TAG = NotificationPagePresenter.class.getName();

    private final NotificationPageContract.ViewModel mViewModel;

    public NotificationPagePresenter(NotificationPageContract.ViewModel viewModel) {
        mViewModel = viewModel;
    }

    @Override
    public void onStart() {
        getListNotification();
    }

    @Override
    public void onStop() {

    }

    private void getListNotification() {
        //TODO: get list notification
    }
}
