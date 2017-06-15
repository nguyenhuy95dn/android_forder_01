package com.framgia.forder.screen.domainmanagement.editdomain;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;

/**
 * Exposes the data to be used in the Editdomain screen.
 */

public class EditdomainViewModel extends BaseObservable implements EditdomainContract.ViewModel {

    private final Context mContext;
    private final DomainManagement mDomainManagement;
    private final EditDomainListener mEditDomainListener;
    private EditdomainContract.Presenter mPresenter;
    private String mNameDomain;
    private String mStatus;
    private boolean isChecked;

    EditdomainViewModel(Context context, DomainManagement domainManagement,
            EditDomainListener editDomainListener) {
        mContext = context;
        mDomainManagement = domainManagement;
        mEditDomainListener = editDomainListener;
        getDomain(domainManagement);
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
    public void setPresenter(EditdomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onRequestEditDomain() {
        mEditDomainListener.onRequestEditDomain(mNameDomain, mStatus);
    }

    @Bindable
    public String getNameDomain() {
        return mNameDomain;
    }

    public void setNameDomain(String nameDomain) {
        mNameDomain = nameDomain;
        notifyPropertyChanged(BR.nameDomain);
    }

    @Bindable
    public boolean isCheckedSecret() {
        return isChecked;
    }

    @Bindable
    public boolean isCheckedProfessed() {
        return !isChecked;
    }

    private void getDomain(DomainManagement domainManagement) {
        mNameDomain = domainManagement.getName();
        mStatus = domainManagement.getStatus();
        if (mStatus.equals(mContext.getString(R.string.secret))) {
            isChecked = true;
            notifyPropertyChanged(BR.checkedProfessed);
            notifyPropertyChanged(BR.checkedSecret);
        }
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

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
