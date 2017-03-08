package com.example.duong.android_forder_01.ui.domain;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.databinding.ActivityDomainBinding;
import com.example.duong.android_forder_01.ui.adapter.DomainViewPagerAdapter;

/**
 * Created by Vinh on 03/03/2017.
 */
public class DomainActivity extends AppCompatActivity implements DomainContract.View {
    private DomainContract.Presenter mPresenter;
    private ActivityDomainBinding mActivityDomainBinding;
    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDomainBinding = DataBindingUtil.setContentView(this, R.layout.activity_domain);
        setPresenter(new DomainPresenter(this));
        mPresenter.start();
    }

    public void initViewPager() {
        mTabLayout = mActivityDomainBinding.tabLayoutDomain;
        mViewPager = mActivityDomainBinding.viewPagerDomain;
        FragmentManager manager = getSupportFragmentManager();
        DomainViewPagerAdapter adapter = new DomainViewPagerAdapter(manager, this);
        mViewPager.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        mTabLayout.setupWithViewPager(mViewPager);
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
