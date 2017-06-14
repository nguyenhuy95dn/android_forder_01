package com.framgia.forder.screen.domainmanagement;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;

import static com.framgia.forder.utils.Constant.FORMAT_PRODUCT;
import static com.framgia.forder.utils.Constant.FORMAT_SHOP;
import static com.framgia.forder.utils.Constant.FORMAT_USER;

/**
 * Created by Age on 6/8/2017.
 */

public class ItemDomainManagementViewModel extends BaseObservable {
    private static final String SECRET_STATUS = "secret";
    private static final String PROFESSED_STATUS = "professed";
    private static final String DEFAULT_STATUS = "default";
    private static final String MEMBER = "member";

    private final DomainManagement mDomainManagement;
    private final DomainManagementListener mDomainManagementListener;
    private int mImageStatus;
    private boolean mIsMember;

    ItemDomainManagementViewModel(DomainManagement domainManagement,
            DomainManagementListener domainManagementListener) {
        mDomainManagement = domainManagement;
        mDomainManagementListener = domainManagementListener;
        mIsMember = false;
        initValueStatus();
        checkMember();
    }

    private void initValueStatus() {
        switch (mDomainManagement.getStatus()) {
            case SECRET_STATUS:
                mImageStatus = R.drawable.ic_lock;
                break;
            case PROFESSED_STATUS:
                mImageStatus = R.drawable.ic_public;
                break;
            case DEFAULT_STATUS:
                mImageStatus = R.drawable.ic_lock;
                break;
            default:
                break;
        }
        notifyPropertyChanged(BR.imageStatus);
    }

    private void checkMember() {
        if (mDomainManagement.getRoleOfCurrentUser().equals(MEMBER)) {
            mIsMember = true;
        }
    }

    public void onClickShowListUserInDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onGetListUserInDomain(mDomainManagement.getId());
    }

    public void onClickShowListShopInDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onGetListShopInDomain(mDomainManagement.getId());
    }

    public void onClickLeaveDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onLeaveDomain(mDomainManagement.getId());
    }

    public void onClickEditDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onEditDomain(mDomainManagement.getId());
    }

    public void onClickDeleteDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onDeleteDomain(mDomainManagement.getId());
    }

    public String getName() {
        return mDomainManagement.getName();
    }

    public String getTotalUser() {
        return mDomainManagement.getCountUser() + FORMAT_USER;
    }

    public String getTotalShop() {
        return mDomainManagement.getCountShop() + FORMAT_SHOP;
    }

    public String getTotalProduct() {
        return mDomainManagement.getCountShop() + FORMAT_PRODUCT;
    }

    @Bindable
    public int getImageStatus() {
        return mImageStatus;
    }

    public boolean isMember() {
        return mIsMember;
    }

    public boolean isOwner() {
        return !mIsMember;
    }
}
