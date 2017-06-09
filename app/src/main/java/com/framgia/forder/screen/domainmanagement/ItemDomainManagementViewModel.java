package com.framgia.forder.screen.domainmanagement;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.DomainManagement;

/**
 * Created by Age on 6/8/2017.
 */

public class ItemDomainManagementViewModel extends BaseObservable {
    private final DomainManagement mDomainManagement;
    private final DomainManagementListener mDomainManagementListener;

    ItemDomainManagementViewModel(DomainManagement domainManagement,
            DomainManagementListener domainManagementListener) {
        mDomainManagement = domainManagement;
        mDomainManagementListener = domainManagementListener;
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

    public String getName() {
        return mDomainManagement.getName();
    }

    public String getTotalUser() {
        return String.valueOf(mDomainManagement.getCountUser());
    }

    public String getTotalShop() {
        return String.valueOf(mDomainManagement.getCountShop());
    }

    public String getTotalProduct() {
        return String.valueOf(mDomainManagement.getCountProduct());
    }
}
