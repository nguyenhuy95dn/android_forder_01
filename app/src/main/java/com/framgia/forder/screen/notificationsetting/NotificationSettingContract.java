package com.framgia.forder.screen.notificationsetting;

import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface NotificationSettingContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onUpdateNotificationSettingSuccess();

        void onError(BaseException e);

        void onGetNotificationSettingSuccess(NotificationSettingRequest.Setting userSetting);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void updateNotificationSetting(NotificationSettingRequest notificationSettingRequest);
    }
}
