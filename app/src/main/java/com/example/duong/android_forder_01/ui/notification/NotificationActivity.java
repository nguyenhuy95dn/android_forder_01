package com.example.duong.android_forder_01.ui.notification;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Notification;
import com.example.duong.android_forder_01.databinding.ActivityNotificationBinding;
import com.example.duong.android_forder_01.ui.adapter.NotificationAdapter;
import com.example.duong.android_forder_01.utils.BaseActivity;

import java.util.ArrayList;
import java.util.List;

public class NotificationActivity extends BaseActivity implements NotificationContract.View {
    private NotificationContract.Presenter mPresenter;
    private ActivityNotificationBinding mBinding;
    private List<Notification> mListNotification = new ArrayList<>();
    private ObservableField<NotificationAdapter> mNotificationAdapter = new ObservableField<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_notification);
        setPresenter(new NotificationPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mBinding.setNotificationActivity(this);
        initRecyclerNotification();
    }

    @Override
    public void setPresenter(NotificationContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<NotificationAdapter> getNotificationAdapter() {
        return mNotificationAdapter;
    }

    @Override
    public void initRecyclerNotification() {
        mNotificationAdapter.set(new NotificationAdapter(mListNotification, this));
    }
}
