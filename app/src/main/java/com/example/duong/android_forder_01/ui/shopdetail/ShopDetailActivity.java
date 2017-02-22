package com.example.duong.android_forder_01.ui.shopdetail;

import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.databinding.ActivityShopDetailBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopDetailAdapter;

public class ShopDetailActivity extends AppCompatActivity implements ShopDetailContract.View {
    private ShopDetailContract.Presenter mPresenter;
    private ActivityShopDetailBinding mBinding;
    private ObservableField<ShopDetailAdapter> mAdapter = new ObservableField<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_detail);
        setPresenter(new ShopDetailPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mBinding.setShopDetail(this);
        mAdapter.set(new ShopDetailAdapter(getSupportFragmentManager(), this));
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void setPresenter(ShopDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ShopDetailAdapter> getAdapter() {
        return mAdapter;
    }
}
