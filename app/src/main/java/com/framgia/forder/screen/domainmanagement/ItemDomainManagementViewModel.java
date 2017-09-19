package com.framgia.forder.screen.domainmanagement;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.view.MenuItem;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;

import static com.framgia.forder.utils.Constant.FORMAT_PRODUCT;

/**
 * Created by Age on 6/8/2017.
 */

public class ItemDomainManagementViewModel extends BaseObservable {
    private static final String SECRET_STATUS = "secret";
    private static final String PROFESSED_STATUS = "professed";
    private static final String DEFAULT_STATUS = "default";
    private static final String MEMBER = "member";

    private final Context mContext;
    private final DomainManagement mDomainManagement;
    private final DomainManagementListener mDomainManagementListener;
    private int mImageStatus;
    private boolean mIsMember;

    ItemDomainManagementViewModel(Context context, DomainManagement domainManagement,
            DomainManagementListener domainManagementListener) {
        mContext = context;
        mDomainManagement = domainManagement;
        mDomainManagementListener = domainManagementListener;
        mIsMember = false;
        initValueStatus();
        checkMember();
    }

    private void initValueStatus() {
        switch (mDomainManagement.getStatus()) {
            case SECRET_STATUS:
                mImageStatus = R.drawable.ic_lock_orange;
                break;
            case PROFESSED_STATUS:
                mImageStatus = R.drawable.ic_public_orange;
                break;
            case DEFAULT_STATUS:
                mImageStatus = R.drawable.ic_lock_orange;
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
        mDomainManagementListener.onClickShowListUserInDomain(mDomainManagement);
    }

    public void onClickShowListShopInDomain() {
        if (mDomainManagementListener == null) {
            return;
        }
        mDomainManagementListener.onClickShowListShopInDomain(mDomainManagement);
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
        mDomainManagementListener.onEditDomain(mDomainManagement);
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
        return mDomainManagement.getCountUser() + " " + mContext.getString(R.string.user_unit);
    }

    public String getTotalShop() {
        return mDomainManagement.getCountShop() + " " + mContext.getString(R.string.shop_unit);
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

    public boolean isOwnerMember() {
        return mDomainManagement.isOwner();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.view_user:
                onClickShowListUserInDomain();
                return true;
            case R.id.view_shop:
                onClickShowListShopInDomain();
                return true;
            default:
        }
        return false;
    }
}
