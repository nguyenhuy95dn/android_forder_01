package com.example.duong.android_forder_01.ui.shopmanagementdetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.ShopManagement;
import com.example.duong.android_forder_01.databinding.ActivityShopManagementDetailBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopManagementDetailAdapter;
import com.example.duong.android_forder_01.utils.BaseActivity;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

public class ShopManagementDetailActivity extends BaseActivity implements
    ShopManagementDetailContract.View {
    private ShopManagementDetailContract.Presenter mPresenter;
    private ActivityShopManagementDetailBinding mBinding;
    private ShopManagement mShopManagement;
    private ObservableField<ShopManagementDetailAdapter> mAdapter = new ObservableField<>();

    public static Intent getShopManagementDetailIntent(Context context, ShopManagement shop) {
        Intent intent = new Intent(context, ShopManagementDetailActivity.class);
        intent.putExtra(EXTRA_SHOP, shop);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_management_detail);
        setPresenter(new ShopManagementDetailPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mShopManagement = (ShopManagement) getIntent().getSerializableExtra(EXTRA_SHOP);
        mBinding.setShopManagementDetail(this);
        mAdapter.set(new ShopManagementDetailAdapter(getSupportFragmentManager(), this,
            mShopManagement));
    }

    @Override
    public void setPresenter(ShopManagementDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ShopManagementDetailAdapter> getAdapter() {
        return mAdapter;
    }
}
