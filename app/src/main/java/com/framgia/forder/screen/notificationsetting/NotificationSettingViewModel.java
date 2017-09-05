package com.framgia.forder.screen.notificationsetting;

/**
 * Exposes the data to be used in the Notificationsetting screen.
 */

public class NotificationSettingViewModel implements NotificationSettingContract.ViewModel {

    private NotificationSettingContract.Presenter mPresenter;

    NotificationSettingViewModel() {
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(NotificationSettingContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
