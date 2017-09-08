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
    private String mIdRoomChatwork;
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
        mEditDomainListener.onRequestEditDomain(mDomainManagement.getId(), mNameDomain, mStatus,
                mIdRoomChatwork);
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
    public String getIdRoomChatwork() {
        return String.valueOf(mIdRoomChatwork);
    }

    public void setIdRoomChatwork(String idRoomChatwork) {
        mIdRoomChatwork = idRoomChatwork;
        notifyPropertyChanged(BR.idRoomChatwork);
    }

    public boolean isCheckedSecret() {
        return isChecked;
    }

    public boolean isCheckedProfessed() {
        return !isChecked;
    }

    private void getDomain(DomainManagement domainManagement) {
        mNameDomain = domainManagement.getName();
        mStatus = domainManagement.getStatus();
        if (domainManagement.getRoomChatwork() != null) {
            mIdRoomChatwork = String.valueOf(domainManagement.getRoomChatwork());
        } else {
            mIdRoomChatwork = "";
        }
        if (mStatus.equals(mContext.getString(R.string.secret))) {
            isChecked = true;
        }
    }

    public void onCheckedSecret() {
        if (!isChecked) {
            isChecked = true;
            mStatus = mContext.getString(R.string.professed);
        }
    }

    public void onCheckedProfessed() {
        if (isChecked) {
            isChecked = false;
            mStatus = mContext.getString(R.string.secret);
        }
    }

    public String getStatus() {
        return mStatus;
    }

    public void setStatus(String status) {
        mStatus = status;
    }
}
