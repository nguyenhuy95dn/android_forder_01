package com.example.duong.android_forder_01.ui.productdetail;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.databinding.ActivityProductDetailBinding;

public class ProductDetailActivity extends AppCompatActivity implements ProductDetailContract.View {
    private ActivityProductDetailBinding mBinding;
    private ProductDetailContract.Presenter mPresenter;
    private Toolbar mToolbar;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_product_detail);
        setPresenter(new ProductDetailPresenter(this));
        mPresenter.start();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_home, menu);
        MenuItem item = menu.findItem(R.id.item_spinner);
        item.setVisible(false);
        return true;
    }

    @Override
    public void initToolbar() {
        mToolbar = mBinding.toolbarProductDetail;
        mToolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public void start() {
        initToolbar();
    }

    @Override
    public void setPresenter(ProductDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
