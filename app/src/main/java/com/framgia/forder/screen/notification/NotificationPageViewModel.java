package com.framgia.forder.screen.notification;

import android.databinding.BaseObservable;
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

    private NotificationPageContract.Presenter mPresenter;
    private final NotificationPageAdapter mNotificationPageAdapter;
    private final Navigator mNavigator;

    NotificationPageViewModel(NotificationPageAdapter notificationPageAdapter,
            Navigator navigator) {
        mNotificationPageAdapter = notificationPageAdapter;
        mNavigator = navigator;
        mNotificationPageAdapter.setItemClickListener(this);
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

    }

    public NotificationPageAdapter getNotificationPageAdapter() {
        return mNotificationPageAdapter;
    }
}
