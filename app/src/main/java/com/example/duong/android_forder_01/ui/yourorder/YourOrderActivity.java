package com.example.duong.android_forder_01.ui.yourorder;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Orders;
import com.example.duong.android_forder_01.databinding.ActivityYourOrderBinding;
import com.example.duong.android_forder_01.ui.adapter.YourOrderAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Duong on 3/14/2017.
 */
public class YourOrderActivity extends AppCompatActivity implements YourOrderContract.View {
    private ActivityYourOrderBinding mBinding;
    private YourOrderContract.Presenter mPresenter;
    private List<Orders> mOrders = new ArrayList<>();
    private ObservableField<YourOrderAdapter> mYourOrderAdapter = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout
            .activity_shopping_card);
        setPresenter(new YourOrderPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mBinding.setYourOrder(this);
        mBinding.setActionHandler(new YourOrderActionHandler(mPresenter));
        mYourOrderAdapter
            .set(new YourOrderAdapter(this, mOrders, mPresenter));
    }

    @Override
    public void setPresenter(YourOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<YourOrderAdapter> getYourOrderAdapter() {
        return mYourOrderAdapter;
    }
}
