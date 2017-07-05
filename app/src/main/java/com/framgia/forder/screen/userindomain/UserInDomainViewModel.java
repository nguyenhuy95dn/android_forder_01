package com.framgia.forder.screen.userindomain;

import android.content.DialogInterface;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.android.databinding.library.baseAdapters.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Userindomain screen.
 */

public class UserInDomainViewModel extends BaseObservable
        implements UserInDomainContract.ViewModel, UserInDomainListener {

    private static final String MANAGER = "manager";

    private UserInDomainContract.Presenter mPresenter;
    private final UserInDomainAdapter mAdapter;
    private final DomainManagement mDomainManagement;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private boolean mIsProgressBarListUserInDomain;

    UserInDomainViewModel(UserInDomainAdapter adapter, DomainManagement domainManagement,
            Navigator navigator, DialogManager dialogManager) {
        mAdapter = adapter;
        mDomainManagement = domainManagement;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mAdapter.setUserInDomainListener(this);
        setProgressBarListUserInDomain(false);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListUserInDomain(mDomainManagement.getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(UserInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListUserInDomainSuccess(List<User> users, int currentUserId) {
        if (mDomainManagement.getOwner() == currentUserId) {
            mAdapter.setAuthorityInDomain(true);
        }
        mAdapter.updateData(users);
    }

    @Override
    public void onGetListUserInDomainError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onDeleteUserInDomainSuccess() {
        mNavigator.showToastCustomActivity(R.string.delete_success);
        mPresenter.getListUserInDomain(mDomainManagement.getId());
    }

    @Override
    public void onDeleteUserInDomainError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onChangeRoleOfUserSuccess() {
        mNavigator.showToastCustomActivity(R.string.change_role_success);
        mPresenter.getListUserInDomain(mDomainManagement.getId());
    }

    @Override
    public void onChangeRoleOfUserError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
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
    public void onShowProgressBar() {
        setProgressBarListUserInDomain(true);
    }

    @Override
    public void onHideProgressBar() {
        setProgressBarListUserInDomain(false);
    }

    public UserInDomainAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onClickDeleteUser(final int userId) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_delete_user,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteUserInDomain(mDomainManagement.getId(), userId);
                    }
                });
        mDialogManager.show();
    }

    @Override
    public void onClickAddManager(int userId) {
        mPresenter.changeRuleOfUserInDomain(mDomainManagement.getId(), userId, MANAGER);
    }

    @Bindable
    public boolean isProgressBarListUserInDomain() {
        return mIsProgressBarListUserInDomain;
    }

    public void setProgressBarListUserInDomain(boolean progressBarListUserInDomain) {
        mIsProgressBarListUserInDomain = progressBarListUserInDomain;
        notifyPropertyChanged(BR.progressBarListUserInDomain);
    }

    public void onClickAddUserInDomain() {
        //Todo dev later when have API
    }
}
