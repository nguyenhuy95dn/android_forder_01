package com.framgia.forder.screen.notification;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.utils.StatusCode;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageViewModel extends BaseObservable
        implements NotificationPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {

    private NotificationPageContract.Presenter mPresenter;
    private NotificationPageAdapter mNotificationPageAdapter;
    private Navigator mNavigator;
    private Notification mNotification;
    private int mColorBackgroundNotification;
    private BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> mItemClickListener;

    NotificationPageViewModel(NotificationPageAdapter notificationPageAdapter,
            Navigator navigator) {
        mNotificationPageAdapter = notificationPageAdapter;
        mNavigator = navigator;
        mNotificationPageAdapter.setItemClickListener(this);
    }

    NotificationPageViewModel(Notification notification,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> itemClickListener) {
        mNotification = notification;
        mItemClickListener = itemClickListener;
        initColorNotification();
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
    public void setPresenter(NotificationPageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListAllNotificationError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void onGetListAllNotificationSuccess(List<Notification> notifications) {
        mNotificationPageAdapter.updateData(notifications);
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {
        //TODO: Item click
    }

    public void onItemClicked() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mNotification);
    }

    public String getNotificationImage() {
        return "";
    }

    public String getNotificationTitle() {
        return mNotification.getMessage();
    }

    public String getNotificationTime() {
        return mNotification.getTimeNotificationFormat();
    }

    private void initColorNotification() {
        if (mNotification != null) {
            if (mNotification.isRead()) {
                setColorBackgroundNotification(Color.WHITE);
            } else {
                setColorBackgroundNotification(Color.parseColor(StatusCode.COLOR_NOTIFICATION));
            }
        }
    }

    public NotificationPageAdapter getNotificationPageAdapter() {
        return mNotificationPageAdapter;
    }

    @Bindable
    public int getColorBackgroundNotification() {
        return mColorBackgroundNotification;
    }

    public void setColorBackgroundNotification(int colorBackgroundNotification) {
        mColorBackgroundNotification = colorBackgroundNotification;
        notifyPropertyChanged(BR.colorBackgroundNotification);
    }
}
