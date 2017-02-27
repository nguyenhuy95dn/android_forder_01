package com.example.duong.android_forder_01.ui.domain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.databinding.ActivityDomainBinding;

/**
 * Created by Vinh on 03/03/2017.
 */
public class DomainActivity extends AppCompatActivity implements DomainContract.View {
    private DomainContract.Presenter mPresenter;
    private ActivityDomainBinding mActivityDomainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDomainBinding = DataBindingUtil.setContentView(this, R.layout.activity_domain);
        setPresenter(new DomainPresenter(this));
        mPresenter.start();
    }

    public void initViewPager() {
        //TODO: init view pager
    }

    @Override
    public void showGetDataError() {
        //TODO: Show get data error
    }

    @Override
    public void start() {
        initViewPager();
        mActivityDomainBinding.setActivityDomain(this);
    }

    @Override
    public void setPresenter(DomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
