package com.framgia.forder.screen.domainmanagement.adddomain;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the Adddomain screen.
 */

public class AddDomainViewModel extends BaseObservable implements AddDomainContract.ViewModel {

    private final Context mContext;
    private final Navigator mNavigator;
    private AddDomainContract.Presenter mPresenter;
    private String mNameDomain;
    private String mStatus;
    private boolean isChecked;

    AddDomainViewModel(Context context, Navigator navigator) {
        mContext = context;
        mNavigator = navigator;
        isChecked = true;
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
    public void setPresenter(AddDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Bindable
    public String getNameDomain() {
        return mNameDomain;
    }

    public void setNameDomain(String nameDomain) {
        mNameDomain = nameDomain;
        notifyPropertyChanged(BR.nameDomain);
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }

    public void onCheckedSecret() {
        if (isChecked) {
            isChecked = false;
            mStatus = mContext.getString(R.string.secret);
        } else {
            isChecked = true;
            mStatus = mContext.getString(R.string.professed);
        }
    }

    public boolean isCheckedSecret() {
        return isChecked;
    }

    public boolean isCheckedProfessed() {
        return !isChecked;
    }
}
