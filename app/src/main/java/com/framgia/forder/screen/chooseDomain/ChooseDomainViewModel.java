package com.framgia.forder.screen.chooseDomain;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.screen.main.MainActivity;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.screen.splash.SplashActivity.PARAMS;
import static com.framgia.forder.utils.Constant.DEFAULT_VALUE;

/**
 * Exposes the data to be used in the ChooseDomain screen.
 */

public class ChooseDomainViewModel extends BaseObservable
        implements ChooseDomainContract.ViewModel {

    private final Context mContext;
    private ChooseDomainContract.Presenter mPresenter;
    private final ArrayAdapter<String> mAdapter;
    private final List<Domain> mDomains;
    private int mSelectedTypePosition;
    private final Navigator mNavigator;
    private boolean mProgressBarVisible;
    private String mParams;

    ChooseDomainViewModel(Context context, ArrayAdapter<String> adapter, Navigator navigator,
            String params) {
        mContext = context;
        mAdapter = adapter;
        mNavigator = navigator;
        mDomains = new ArrayList<>();
        mParams = params;
        setProgressBarVisible(false);
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
    public void setPresenter(ChooseDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetDomainSuccess(List<Domain> domains) {
        if (domains == null) {
            return;
        }
        mDomains.clear();
        mDomains.addAll(domains);
        mAdapter.add(mContext.getString(R.string.none));
        for (Domain domain : domains) {
            mAdapter.add(domain.getName());
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onGetDomainError(BaseException e) {
        mNavigator.showToastCustom(e.getMessage());
    }

    @Override
    public void onShowProgressBar() {
        setProgressBarVisible(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressBarVisible(false);
    }

    @Bindable
    public Integer getSelectedTypePosition() {
        return mSelectedTypePosition;
    }

    public ArrayAdapter<String> getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isProgressBarVisible() {
        return mProgressBarVisible;
    }

    public void setProgressBarVisible(boolean progressBarVisible) {
        mProgressBarVisible = progressBarVisible;
        notifyPropertyChanged(BR.progressBarVisible);
    }

    public void onClickNext() {
        if (mSelectedTypePosition == DEFAULT_VALUE) {
            mNavigator.showToastCustom(
                    mContext.getString(R.string.you_need_to_choose_a_domain_to_continue));
        } else {
            onShowProgressBar();
            Domain domain = mDomains.get(mSelectedTypePosition - 1);
            mPresenter.saveCurrentDomain(domain);
            Bundle bundle = new Bundle();
            bundle.putString(PARAMS, mParams);
            mNavigator.startActivity(MainActivity.class, bundle);
            mNavigator.finishActivity();
            onHideProgressBar();
        }
    }

    public void onCLickLogout() {
        mNavigator.startActivity(LoginActivity.class);
    }

    public void setSelectedTypePosition(Integer selectedTypePosition) {
        this.mSelectedTypePosition = selectedTypePosition;
    }

    @Override
    public void onReloadData() {
        mPresenter.getListDomain();
    }
}
