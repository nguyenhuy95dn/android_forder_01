package com.framgia.forder.screen.domainmanagement;

import com.framgia.forder.data.model.DomainManagement;

/**
 * Created by Age on 6/8/2017.
 */

public interface DomainManagementListener {
    void onClickShowListUserInDomain(DomainManagement domainManagement);

    void onClickShowListShopInDomain(DomainManagement domainManagement);

    void onLeaveDomain(int domainId);

    void onEditDomain(DomainManagement domainManagement);

    void onDeleteDomain(int domainId);
}
