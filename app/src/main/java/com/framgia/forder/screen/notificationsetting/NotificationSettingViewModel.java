package com.framgia.forder.screen.notificationsetting;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.NotificationSettingRequest;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Notificationsetting screen.
 */

public class NotificationSettingViewModel extends BaseObservable
        implements NotificationSettingContract.ViewModel {

    private static final String TAG = "NotificationSetting";

    private NotificationSettingContract.Presenter mPresenter;
    private static final int STATUS_ON = 1;
    private static final int STATUS_OFF = 0;

    private final Navigator mNavigator;
    private boolean mIsAllNotification;
    private boolean mIsAllEmail;
    private boolean mIsAllChatwork;
    private boolean mIsNewOrderRequestNotification;
    private boolean mIsNewOrderRequestEmail;
    private boolean mIsRequestProcessedNotification;
    private boolean mIsRequestProcessedEmail;
    private boolean mIsRequestProcessedChatwork;
    private boolean mIsCreateOrderNotification;
    private boolean mIsCreateOrderEmail;
    private boolean mIsOpenShopChatwork;
    private int mStatusNewOrderRequestNotification;
    private int mStatusNewOrderRequestEmail;
    private int mStatusRequestProcessedNotification;
    private int mStatusRequestProcessedEmail;
    private int mStatusRequestProcessedChatwork;
    private int mStatusCreateOrderNotification;
    private int mStatusCreateOrderEmail;
    private int mStatusOpenShopChatwork;

    NotificationSettingViewModel(Navigator navigator) {
        mNavigator = navigator;
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

    @Bindable
    public boolean isAllNotification() {
        return mIsAllNotification;
    }

    private void setAllNotification(boolean allNotification) {
        mIsAllNotification = allNotification;
        notifyPropertyChanged(BR.allNotification);
    }

    @Bindable
    public boolean isAllEmail() {
        return mIsAllEmail;
    }

    private void setAllEmail(boolean allEmail) {
        mIsAllEmail = allEmail;
        notifyPropertyChanged(BR.allEmail);
    }

    @Bindable
    public boolean isAllChatwork() {
        return mIsAllChatwork;
    }

    private void setAllChatwork(boolean allChatwork) {
        mIsAllChatwork = allChatwork;
        notifyPropertyChanged(BR.allChatwork);
    }

    @Bindable
    public boolean isNewOrderRequestNotification() {
        return mIsNewOrderRequestNotification;
    }

    private void setNewOrderRequestNotification(boolean newOrderRequestNotification) {
        mIsNewOrderRequestNotification = newOrderRequestNotification;
        notifyPropertyChanged(BR.newOrderRequestNotification);
    }

    @Bindable
    public boolean isNewOrderRequestEmail() {
        return mIsNewOrderRequestEmail;
    }

    private void setNewOrderRequestEmail(boolean newOrderRequestEmail) {
        mIsNewOrderRequestEmail = newOrderRequestEmail;
        notifyPropertyChanged(BR.newOrderRequestEmail);
    }

    @Bindable
    public boolean isRequestProcessedNotification() {
        return mIsRequestProcessedNotification;
    }

    private void setRequestProcessedNotification(boolean requestProcessedNotification) {
        mIsRequestProcessedNotification = requestProcessedNotification;
        notifyPropertyChanged(BR.requestProcessedNotification);
    }

    @Bindable
    public boolean isRequestProcessedEmail() {
        return mIsRequestProcessedEmail;
    }

    private void setRequestProcessedEmail(boolean requestProcessedEmail) {
        mIsRequestProcessedEmail = requestProcessedEmail;
        notifyPropertyChanged(BR.requestProcessedEmail);
    }

    @Bindable
    public boolean isRequestProcessedChatwork() {
        return mIsRequestProcessedChatwork;
    }

    private void setRequestProcessedChatwork(boolean requestProcessedChatwork) {
        mIsRequestProcessedChatwork = requestProcessedChatwork;
        notifyPropertyChanged(BR.requestProcessedChatwork);
    }

    @Bindable
    public boolean isCreateOrderNotification() {
        return mIsCreateOrderNotification;
    }

    private void setCreateOrderNotification(boolean createOrderNotification) {
        mIsCreateOrderNotification = createOrderNotification;
        notifyPropertyChanged(BR.createOrderNotification);
    }

    @Bindable
    public boolean isCreateOrderEmail() {
        return mIsCreateOrderEmail;
    }

    private void setCreateOrderEmail(boolean createOrderEmail) {
        mIsCreateOrderEmail = createOrderEmail;
        notifyPropertyChanged(BR.createOrderEmail);
    }

    @Bindable
    public boolean isOpenShopChatwork() {
        return mIsOpenShopChatwork;
    }

    private void setOpenShopChatwork(boolean openShopChatwork) {
        mIsOpenShopChatwork = openShopChatwork;
        notifyPropertyChanged(BR.openShopChatwork);
    }

    public void onClickCheckAllNotification() {
        if (!isAllNotification()) {
            setAllNotification(true);
            setNewOrderRequestNotification(true);
            setRequestProcessedNotification(true);
            setCreateOrderNotification(true);
            mStatusNewOrderRequestNotification = STATUS_ON;
            mStatusRequestProcessedNotification = STATUS_ON;
            mStatusCreateOrderNotification = STATUS_ON;
        } else {
            setAllNotification(false);
            setNewOrderRequestNotification(false);
            setRequestProcessedNotification(false);
            setCreateOrderNotification(false);
            mStatusNewOrderRequestNotification = STATUS_OFF;
            mStatusRequestProcessedNotification = STATUS_OFF;
            mStatusCreateOrderNotification = STATUS_OFF;
        }
    }

    public void onClickCheckAllEmail() {
        if (!isAllEmail()) {
            setAllEmail(true);
            setNewOrderRequestEmail(true);
            setRequestProcessedEmail(true);
            setCreateOrderEmail(true);
            mStatusNewOrderRequestEmail = STATUS_ON;
            mStatusRequestProcessedEmail = STATUS_ON;
            mStatusCreateOrderEmail = STATUS_ON;
        } else {
            setAllEmail(false);
            setNewOrderRequestEmail(false);
            setRequestProcessedEmail(false);
            setCreateOrderEmail(false);
            mStatusNewOrderRequestEmail = STATUS_OFF;
            mStatusRequestProcessedEmail = STATUS_OFF;
            mStatusCreateOrderEmail = STATUS_OFF;
        }
    }

    public void onClickCheckAllChatwork() {
        if (!isAllChatwork()) {
            setAllChatwork(true);
            setRequestProcessedChatwork(true);
            setOpenShopChatwork(true);
            mStatusRequestProcessedChatwork = STATUS_ON;
            mStatusOpenShopChatwork = STATUS_ON;
        } else {
            setAllChatwork(false);
            setRequestProcessedChatwork(false);
            setOpenShopChatwork(false);
            mStatusRequestProcessedChatwork = STATUS_OFF;
            mStatusOpenShopChatwork = STATUS_OFF;
        }
    }

    public void onClickCheckNewOrderRequest() {
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setNewOrderRequestNotification(false);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setNewOrderRequestNotification(true);
            setAllNotification(true);
            mStatusNewOrderRequestNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setNewOrderRequestNotification(true);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setNewOrderRequestNotification(true);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setNewOrderRequestNotification(true);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_ON;
            return;
        }
        if (isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setNewOrderRequestNotification(false);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_OFF;
            return;
        }
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setNewOrderRequestNotification(false);
            setAllNotification(false);
            mStatusNewOrderRequestNotification = STATUS_OFF;
            return;
        }
        setNewOrderRequestNotification(false);
        setAllNotification(false);
        mStatusNewOrderRequestNotification = STATUS_OFF;
    }

    public void onClickRequestProcessedNotification() {
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setRequestProcessedNotification(false);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setRequestProcessedNotification(true);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setRequestProcessedNotification(false);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setRequestProcessedNotification(true);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setRequestProcessedNotification(false);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_OFF;
            return;
        }
        if (isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setRequestProcessedNotification(true);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_ON;
            return;
        }
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setRequestProcessedNotification(false);
            setAllNotification(false);
            mStatusRequestProcessedNotification = STATUS_OFF;
            return;
        }
        setRequestProcessedNotification(true);
        setAllNotification(true);
        mStatusRequestProcessedNotification = STATUS_ON;
    }

    public void onClickCreateOrderNotification() {
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setCreateOrderNotification(false);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setCreateOrderNotification(true);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setCreateOrderNotification(false);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && isCreateOrderNotification()) {
            setCreateOrderNotification(false);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setCreateOrderNotification(true);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_ON;
            return;
        }
        if (isNewOrderRequestNotification()
                && !isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setCreateOrderNotification(true);
            setAllNotification(false);
            mStatusCreateOrderNotification = STATUS_ON;
            return;
        }
        if (isNewOrderRequestNotification()
                && isRequestProcessedNotification()
                && !isCreateOrderNotification()) {
            setCreateOrderNotification(true);
            setAllNotification(true);
            mStatusCreateOrderNotification = STATUS_ON;
            return;
        }
        setCreateOrderNotification(false);
        setAllNotification(false);
        mStatusCreateOrderNotification = STATUS_OFF;
    }

    public void onClickCheckNewOrderRequestEmail() {
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setNewOrderRequestEmail(false);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setNewOrderRequestEmail(true);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setNewOrderRequestEmail(true);
            setAllEmail(true);
            mStatusNewOrderRequestEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && isCreateOrderEmail()) {
            setNewOrderRequestEmail(true);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setNewOrderRequestEmail(true);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_ON;
            return;
        }
        if (isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setNewOrderRequestEmail(false);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_OFF;
            return;
        }
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setNewOrderRequestEmail(false);
            setAllEmail(false);
            mStatusNewOrderRequestEmail = STATUS_OFF;
            return;
        }
        setNewOrderRequestEmail(false);
        setAllEmail(false);
        mStatusNewOrderRequestEmail = STATUS_OFF;
    }

    public void onClickRequestProcessedEmail() {
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setRequestProcessedEmail(false);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setRequestProcessedEmail(true);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setRequestProcessedEmail(false);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && isCreateOrderEmail()) {
            setRequestProcessedEmail(true);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setRequestProcessedEmail(false);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_OFF;
            return;
        }
        if (isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setRequestProcessedEmail(true);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_ON;
            return;
        }
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setRequestProcessedEmail(false);
            setAllEmail(false);
            mStatusRequestProcessedEmail = STATUS_OFF;
            return;
        }
        setRequestProcessedEmail(true);
        setAllEmail(true);
        mStatusRequestProcessedEmail = STATUS_ON;
    }

    public void onClickCreateOrderEmail() {
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setCreateOrderEmail(false);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setCreateOrderEmail(true);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_ON;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && isCreateOrderEmail()) {
            setCreateOrderEmail(false);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && !isRequestProcessedEmail() && isCreateOrderEmail()) {
            setCreateOrderEmail(false);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_OFF;
            return;
        }
        if (!isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setCreateOrderEmail(true);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_ON;
            return;
        }
        if (isNewOrderRequestEmail() && !isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setCreateOrderEmail(true);
            setAllEmail(false);
            mStatusCreateOrderEmail = STATUS_ON;
            return;
        }
        if (isNewOrderRequestEmail() && isRequestProcessedEmail() && !isCreateOrderEmail()) {
            setCreateOrderEmail(true);
            setAllEmail(true);
            mStatusCreateOrderEmail = STATUS_ON;
            return;
        }
        setCreateOrderEmail(false);
        setAllEmail(false);
        mStatusCreateOrderEmail = STATUS_OFF;
    }

    public void onClickRequestProcessedChatwork() {
        if (isRequestProcessedChatwork() && isOpenShopChatwork()) {
            setRequestProcessedChatwork(false);
            setAllChatwork(false);
            mStatusRequestProcessedChatwork = STATUS_OFF;
            return;
        }
        if (!isRequestProcessedChatwork() && !isOpenShopChatwork()) {
            setRequestProcessedChatwork(true);
            setAllChatwork(false);
            mStatusRequestProcessedChatwork = STATUS_ON;
            return;
        }
        if (isRequestProcessedChatwork() && !isOpenShopChatwork()) {
            setRequestProcessedChatwork(false);
            setAllChatwork(false);
            mStatusRequestProcessedChatwork = STATUS_OFF;
            return;
        }
        setRequestProcessedChatwork(true);
        setAllChatwork(true);
        mStatusRequestProcessedChatwork = STATUS_ON;
    }

    public void onClickOpenShopChatwork() {
        if (isRequestProcessedChatwork() && isOpenShopChatwork()) {
            setOpenShopChatwork(false);
            setAllChatwork(false);
            mStatusOpenShopChatwork = STATUS_OFF;
            return;
        }
        if (!isRequestProcessedChatwork() && !isOpenShopChatwork()) {
            setOpenShopChatwork(true);
            setAllChatwork(false);
            mStatusOpenShopChatwork = STATUS_ON;
            return;
        }
        if (isRequestProcessedChatwork() && !isOpenShopChatwork()) {
            setOpenShopChatwork(true);
            setAllChatwork(true);
            mStatusOpenShopChatwork = STATUS_ON;
            return;
        }
        setOpenShopChatwork(false);
        setAllChatwork(false);
        mStatusOpenShopChatwork = STATUS_OFF;
    }

    public void onClickUpdateNotificationSetting() {
        NotificationSettingRequest notificationSettingRequest = new NotificationSettingRequest();
        NotificationSettingRequest.Setting setting = new NotificationSettingRequest.Setting();

        NotificationSettingRequest.Setting.NotificationAndEmailSetting notificationSetting =
                new NotificationSettingRequest.Setting.NotificationAndEmailSetting();
        notificationSetting.setOrderRequest(mStatusNewOrderRequestNotification);
        notificationSetting.setOrderProcessed(mStatusRequestProcessedNotification);
        notificationSetting.setSendOrder(mStatusCreateOrderNotification);

        NotificationSettingRequest.Setting.NotificationAndEmailSetting emailSetting =
                new NotificationSettingRequest.Setting.NotificationAndEmailSetting();
        emailSetting.setOrderRequest(mStatusNewOrderRequestEmail);
        emailSetting.setOrderProcessed(mStatusRequestProcessedEmail);
        emailSetting.setSendOrder(mStatusCreateOrderEmail);

        NotificationSettingRequest.Setting.ChatworkSetting chatworkSetting =
                new NotificationSettingRequest.Setting.ChatworkSetting();
        chatworkSetting.setChatworkProcessed(mStatusRequestProcessedChatwork);
        chatworkSetting.setShopOpen(mStatusOpenShopChatwork);

        setting.setNotificationSettings(notificationSetting);
        setting.setEmailSettings(emailSetting);
        setting.setChatworkSettings(chatworkSetting);

        notificationSettingRequest.setUserSetting(setting);

        mPresenter.updateNotificationSetting(notificationSettingRequest);
    }

    @Override
    public void onUpdateNotificationSettingSuccess() {
        mNavigator.showToastCustomActivity(R.string.update_success);
    }

    @Override
    public void onError(BaseException e) {
        Log.e(TAG, "onError: ", e);
    }

    @Override
    public void onGetNotificationSettingSuccess(NotificationSettingRequest.Setting userSetting) {
        getDataNotificationSetting(userSetting);
    }

    private void getDataNotificationSetting(NotificationSettingRequest.Setting setting) {
        if (setting != null && setting.getNotificationSettings() != null) {
            mStatusNewOrderRequestNotification =
                    setting.getNotificationSettings().getOrderRequest();
            mStatusRequestProcessedNotification =
                    setting.getNotificationSettings().getOrderProcessed();
            mStatusCreateOrderNotification = setting.getNotificationSettings().getSendOrder();

            setNewOrderRequestNotification(
                    setting.getNotificationSettings().getOrderRequest() == STATUS_ON);
            setRequestProcessedNotification(
                    setting.getNotificationSettings().getOrderProcessed() == STATUS_ON);
            setCreateOrderNotification(
                    setting.getNotificationSettings().getSendOrder() == STATUS_ON);

            if (setting.getNotificationSettings().getOrderRequest() == STATUS_ON
                    && setting.getNotificationSettings().getOrderProcessed() == STATUS_ON
                    && setting.getNotificationSettings().getSendOrder() == STATUS_ON) {
                setAllNotification(true);
            }
        }
        if (setting != null && setting.getEmailSettings() != null) {
            mStatusNewOrderRequestEmail = setting.getEmailSettings().getOrderRequest();
            mStatusRequestProcessedEmail = setting.getEmailSettings().getOrderProcessed();
            mStatusCreateOrderEmail = setting.getEmailSettings().getSendOrder();

            setNewOrderRequestEmail(setting.getEmailSettings().getOrderRequest() == STATUS_ON);
            setRequestProcessedEmail(setting.getEmailSettings().getOrderProcessed() == STATUS_ON);
            setCreateOrderEmail(setting.getEmailSettings().getSendOrder() == STATUS_ON);

            if (setting.getEmailSettings().getOrderRequest() == STATUS_ON
                    && setting.getEmailSettings().getOrderProcessed() == STATUS_ON
                    && setting.getEmailSettings().getSendOrder() == STATUS_ON) {
                setAllEmail(true);
            }
        }
        if (setting != null && setting.getChatworkSettings() != null) {
            mStatusRequestProcessedChatwork = setting.getChatworkSettings().getChatworkProcessed();
            mStatusOpenShopChatwork = setting.getChatworkSettings().getShopOpen();

            setRequestProcessedChatwork(
                    setting.getChatworkSettings().getChatworkProcessed() == STATUS_ON);
            setOpenShopChatwork(setting.getChatworkSettings().getShopOpen() == STATUS_ON);

            if (setting.getChatworkSettings().getChatworkProcessed() == STATUS_ON
                    && setting.getChatworkSettings().getShopOpen() == STATUS_ON) {
                setAllChatwork(true);
            }
        }
    }
}
