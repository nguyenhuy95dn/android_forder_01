package com.framgia.forder.screen.domainmanagement;

/**
 * Created by Age on 6/8/2017.
 */

public interface DomainManagementListener {
    void onGetListUserInDomain(int domainId);

    void onGetListShopInDomain(int domainId);

    void onLeaveDomain(int domainId);

    void onEditDomain(int domainId);

    void onDeleteDomain(int domainId);
}
