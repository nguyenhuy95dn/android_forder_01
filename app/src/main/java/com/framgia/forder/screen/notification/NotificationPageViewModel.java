package com.framgia.forder.screen.notification;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.util.Log;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Created by ASUS on 24-04-2017.
 */

public class NotificationPageViewModel extends BaseObservable
        implements NotificationPageContract.ViewModel,
        BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<Object> {
    private static final String TAG = "NotificationPageViewMod";

    private NotificationPageContract.Presenter mPresenter;
    private final NotificationPageAdapter mNotificationPageAdapter;
    private final Navigator mNavigator;
    private boolean mIsHaveData;

    NotificationPageViewModel(NotificationPageAdapter notificationPageAdapter,
            Navigator navigator) {
        mNotificationPageAdapter = notificationPageAdapter;
        mNavigator = navigator;
        mNotificationPageAdapter.setItemClickListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListNotification();
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
        Log.e(TAG, "onGetListAllNotificationError: ", e);
        setHaveData(false);
    }

    @Override
    public void onGetListAllNotificationSuccess(List<Notification> notifications) {
        if (notifications.size() == 0) {
            setHaveData(false);
            return;
        }
        setHaveData(true);
        mNotificationPageAdapter.updateData(notifications);
    }

    @Override
    public void reloadData() {
        mPresenter.getListNotification();
    }

    @Override
    public void onItemRecyclerViewClick(Object item) {

    }

    public NotificationPageAdapter getNotificationPageAdapter() {
        return mNotificationPageAdapter;
    }

    @Bindable
    public boolean isHaveData() {
        return mIsHaveData;
    }

    public void setHaveData(boolean haveData) {
        mIsHaveData = haveData;
        notifyPropertyChanged(BR.haveData);
    }
}
