package com.example.duong.android_forder_01.ui.shopdetail;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Shop;
import com.example.duong.android_forder_01.databinding.ActivityShopDetailBinding;
import com.example.duong.android_forder_01.ui.adapter.ShopDetailAdapter;
import com.example.duong.android_forder_01.utils.BaseActivity;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_SHOP;

public class ShopDetailActivity extends BaseActivity implements ShopDetailContract.View {
    private ShopDetailContract.Presenter mPresenter;
    private ActivityShopDetailBinding mBinding;
    private Shop mShop;
    private ObservableField<ShopDetailAdapter> mAdapter = new ObservableField<>();

    public static Intent getShopDetailIntent(Context context, Shop shop) {
        Intent intent = new Intent(context, ShopDetailActivity.class);
        intent.putExtra(EXTRA_SHOP, shop);
        return intent;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_shop_detail);
        setPresenter(new ShopDetailPresenter(this));
        mPresenter.start();
    }

    @Override
    public void start() {
        mShop = (Shop) getIntent().getSerializableExtra(EXTRA_SHOP);
        mBinding.setShopDetail(this);
        mAdapter.set(new ShopDetailAdapter(getSupportFragmentManager(), this, mShop));
    }

    @Override
    public void setPresenter(ShopDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ObservableField<ShopDetailAdapter> getAdapter() {
        return mAdapter;
    }
}
