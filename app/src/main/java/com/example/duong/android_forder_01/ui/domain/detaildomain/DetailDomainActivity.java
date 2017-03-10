package com.example.duong.android_forder_01.ui.domain.detaildomain;

import android.content.Context;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ObservableField;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.duong.android_forder_01.R;
import com.example.duong.android_forder_01.data.model.Domain;
import com.example.duong.android_forder_01.databinding.ActivityDomainDetailBinding;
import com.example.duong.android_forder_01.ui.adapter.DetailDomainViewPageAdapter;

import static com.example.duong.android_forder_01.utils.Const.KeyIntent.EXTRA_DOMAIN;

/**
 * Created by Vinh on 07/03/2017.
 */
public class DetailDomainActivity extends AppCompatActivity implements DetailDomainContract.View {
    private DetailDomainContract.Presenter mPresenter;
    private ActivityDomainDetailBinding mActivityDomainDetailBinding;
    private Domain mDomain;
    private ObservableField<DetailDomainViewPageAdapter> mAdapter = new ObservableField<>();

    public static Intent getDomainDetailIntent(Context context, Domain domain) {
        Intent intent = new Intent(context, DetailDomainActivity.class);
        intent.putExtra(EXTRA_DOMAIN, domain);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivityDomainDetailBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_domain_detail);
        setPresenter(new DetailDomainPresenter(this));
        mPresenter.start();
    }

    @Override
    public void showGetDataError() {
        // TODO: get data error
    }

    @Override
    public void start() {
        mDomain = (Domain) getIntent().getSerializableExtra(EXTRA_DOMAIN);
        mActivityDomainDetailBinding.setActivityDetailDomain(this);
        mAdapter.set(new DetailDomainViewPageAdapter(getSupportFragmentManager(), this, mDomain));
    }

    public ObservableField<DetailDomainViewPageAdapter> getAdapter() {
        return mAdapter;
    }

    @Override
    public void setPresenter(DetailDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }
}
