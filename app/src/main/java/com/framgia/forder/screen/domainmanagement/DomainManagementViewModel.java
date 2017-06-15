package com.framgia.forder.screen.domainmanagement;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.RegisterDomain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainFragment;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainListener;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Domainmanagement screen.
 */

public class DomainManagementViewModel extends BaseObservable
        implements DomainManagementContract.ViewModel, DomainManagementListener, AddDomainListener {
    private static final String TAG = "DomainManagementViewModel";

    private DomainManagementContract.Presenter mPresenter;
    private final DomainManagementAdapter mDomainManagementAdapter;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;

    DomainManagementViewModel(DomainManagementAdapter domainManagementAdapter, Navigator navigator,
            AddDomainFragment addDomainFragment, DialogManager dialogManager) {
        mDomainManagementAdapter = domainManagementAdapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        domainManagementAdapter.setDomainManagementListener(this);
        addDomainFragment.setAddDomainListener(this);
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
    public void setPresenter(DomainManagementContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public DomainManagementAdapter getListDomainManagement() {
        return mDomainManagementAdapter;
    }

    @Override
    public void onGetListDomainManagementSuccess(List<DomainManagement> domainManagementList) {
        mDomainManagementAdapter.updateData(domainManagementList);
    }

    @Override
    public void onGetListDomainManagementError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onRegisterDomainSuccess() {
        mNavigator.showToast(R.string.add_domain_success);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onRegisterDomainError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onGetListUserInDomain(DomainManagement domainManagement) {
        //Todo dev later
    }

    @Override
    public void onGetListShopInDomain(DomainManagement domainManagement) {
        //Todo dev later
    }

    @Override
    public void onLeaveDomain(final int domainId) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_leave_domain,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.leaveDomain(domainId);
                    }
                });
        mDialogManager.show();
    }

    @Override
    public void onLeaveDomainSuccess() {
        mNavigator.showToast(R.string.leave_the_domain_successful);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onLeaveDomainError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onDeleteDomainSuccess() {
        mNavigator.showToast(R.string.delete_the_domain_success);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onDeleteDomainError(BaseException error) {
        mNavigator.showToast(error.getMessage());
    }

    @Override
    public void onEditDomain(DomainManagement domainManagement) {
        //Todo dev later
    }

    @Override
    public void onDeleteDomain(final int domainId) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_delete_domain,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteDomain(domainId);
                    }
                });
        mDialogManager.show();
    }

    public void onClickAddDomain() {
        mNavigator.showAddDomainDialog("AddDomainFragment", this);
    }

    @Override
    public void onRequestRegisterDomain(String nameDomain, String status) {
        RegisterDomainRequest registerDomainRequest = new RegisterDomainRequest();
        RegisterDomain domain = new RegisterDomain();
        domain.setName(nameDomain);
        domain.setStatus(status);
        registerDomainRequest.setDomain(domain);
        mPresenter.registerDomain(registerDomainRequest);
    }
}
