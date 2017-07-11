package com.framgia.forder.screen.adduserindomain;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.AddUserInDomainRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Adduserindomain screen.
 */

public class AddUserInDomainViewModel extends BaseObservable
        implements AddUserInDomainContract.ViewModel,
        AddUserInDomainAdapter.AddUserInDomainListener {

    private AddUserInDomainContract.Presenter mPresenter;
    private final AddUserInDomainAdapter mAdapter;
    private final DomainManagement mDomainManagement;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private boolean mIsProgressBarListUser;

    AddUserInDomainViewModel(AddUserInDomainAdapter adapter, DomainManagement domainManagement,
            Navigator navigator, DialogManager dialogManager) {
        mAdapter = adapter;
        mDomainManagement = domainManagement;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mAdapter.setUserInDomainListener(this);
        setProgressBarListUser(false);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListUser(mDomainManagement.getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(AddUserInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onShowProgressBar() {
        setProgressBarListUser(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressBarListUser(false);
    }

    @Override
    public void onGetListUserSuccess(List<User> users) {
        mAdapter.updateData(users);
    }

    @Override
    public void onMessageError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onAddUserSuccess() {
        mNavigator.showToastCustomActivity(R.string.add_user_success);
        mPresenter.getListUser(mDomainManagement.getId());
    }

    @Override
    public void onShowProgressBarDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBarDialog() {
        mDialogManager.dismissProgressDialog();
    }

    public AddUserInDomainAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isProgressBarListUser() {
        return mIsProgressBarListUser;
    }

    public void setProgressBarListUser(boolean progressBarListUser) {
        mIsProgressBarListUser = progressBarListUser;
        notifyPropertyChanged(BR.progressBarListUser);
    }

    @Override
    public void onClickAddUser(int userId) {
        AddUserInDomainRequest addUserInDomainRequest = new AddUserInDomainRequest();
        AddUserInDomainRequest.UserInDomain user = new AddUserInDomainRequest.UserInDomain();

        user.setUserId(userId);
        user.setDomainId(mDomainManagement.getId());
        addUserInDomainRequest.setUserInDomain(user);

        mPresenter.addUser(addUserInDomainRequest);
    }
}
