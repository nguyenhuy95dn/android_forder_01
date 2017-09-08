package com.framgia.forder.screen.domainmanagement;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.RegisterDomain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainFragment;
import com.framgia.forder.screen.domainmanagement.adddomain.AddDomainListener;
import com.framgia.forder.screen.domainmanagement.editdomain.EditDomainListener;
import com.framgia.forder.screen.domainmanagement.editdomain.EditdomainFragment;
import com.framgia.forder.screen.shopindomain.ShopInDomainFragment;
import com.framgia.forder.screen.userindomain.UserInDomainFragment;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Domainmanagement screen.
 */

public class DomainManagementViewModel extends BaseObservable
        implements DomainManagementContract.ViewModel, DomainManagementListener, AddDomainListener,
        EditDomainListener {
    private static final String TAG = "DomainManagementViewModel";

    private DomainManagementContract.Presenter mPresenter;
    private final DomainManagementAdapter mDomainManagementAdapter;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private boolean mIsProgressBarVisible;

    DomainManagementViewModel(DomainManagementAdapter domainManagementAdapter, Navigator navigator,
            AddDomainFragment addDomainFragment, EditdomainFragment editdomainFragment,
            DialogManager dialogManager) {
        mDomainManagementAdapter = domainManagementAdapter;
        mNavigator = navigator;
        domainManagementAdapter.setDomainManagementListener(this);
        mDialogManager = dialogManager;
        addDomainFragment.setAddDomainListener(this);
        editdomainFragment.setEditDomainListener(this);
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
    public void onGetListDomainManagementSuccess(int userId,
            List<DomainManagement> domainManagementList) {
        for (DomainManagement domainManagement : domainManagementList) {
            if (userId == domainManagement.getOwner()) {
                domainManagement.setOwner(true);
            }
        }
        mDomainManagementAdapter.updateData(domainManagementList);
    }

    @Override
    public void onShowMessageError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onRegisterDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.add_domain_success);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onClickShowListUserInDomain(DomainManagement domainManagement) {
        mNavigator.goNextChildFragment(R.id.layout_content,
                UserInDomainFragment.newInstance(domainManagement), true, Navigator.RIGHT_LEFT,
                "UserInDomainFragment");
    }

    @Override
    public void onClickShowListShopInDomain(DomainManagement domainManagement) {
        mNavigator.goNextChildFragment(R.id.layout_content,
                ShopInDomainFragment.newInstance(domainManagement), true, Navigator.RIGHT_LEFT,
                "ShopInDomainFragment");
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
        mNavigator.showToastCustomActivity(R.string.leave_the_domain_successful);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onDeleteDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.delete_the_domain_success);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onEditDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.update_successful);
        mPresenter.getListDomainManagement();
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
    }

    @Override
    public void onEditDomain(DomainManagement domainManagement) {
        mNavigator.showEditDomainDialog("EditDomainFragment", domainManagement, this);
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
    public void onRequestRegisterDomain(String nameDomain, String status, String idRoomChatwork) {
        RegisterDomainRequest registerDomainRequest = new RegisterDomainRequest();
        RegisterDomain domain = new RegisterDomain();
        domain.setName(nameDomain);
        domain.setStatus(status);
        domain.setRoomChatwork(idRoomChatwork);
        registerDomainRequest.setDomain(domain);
        mPresenter.registerDomain(registerDomainRequest);
    }

    @Override
    public void onRequestEditDomain(int domainId, String nameDomain, String status,
            String idRoomChatwork) {
        mPresenter.editDomain(domainId, nameDomain, status, idRoomChatwork);
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
    public boolean isProgressBarVisible() {
        return mIsProgressBarVisible;
    }

    public void setProgressBarVisible(boolean progressBarVisible) {
        mIsProgressBarVisible = progressBarVisible;
        notifyPropertyChanged(BR.progressBarVisible);
    }
}
