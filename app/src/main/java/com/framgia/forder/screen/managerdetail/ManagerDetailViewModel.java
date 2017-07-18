package com.framgia.forder.screen.managerdetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.model.User;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.utils.StatusCode.ROLE_OWNER;

/**
 * Exposes the data to be used in the ManagerDetail screen.
 */

public class ManagerDetailViewModel extends BaseObservable
        implements ManagerDetailContract.ViewModel {

    private ManagerDetailContract.Presenter mPresenter;
    private final Navigator mNavigator;
    private final User mUser;
    private boolean mIsVisibleRole;

    ManagerDetailViewModel(Navigator navigator, User user) {
        mNavigator = navigator;
        mUser = user;
        setRoleManager();
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
    public void setPresenter(ManagerDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public String getUserName() {
        return mUser.getName();
    }

    public String getEmail() {
        return mUser.getEmail();
    }

    public String getChatWorkId() {
        return mUser.getChatworkId();
    }

    public String getJoinDate() {
        return mUser.getFormatDate();
    }

    public String getDescription() {
        return mUser.getDescription();
    }

    public String getRole() {
        return mUser.getRole();
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            return mUser.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getStatus() {
        //Todo edit later
        return "";
    }

    @Bindable
    public boolean isVisibleRole() {
        return mIsVisibleRole;
    }

    private void setVisibleRole(boolean visibleRole) {
        mIsVisibleRole = visibleRole;
    }

    private void setRoleManager() {
        if (mUser != null) {
            if (mUser.getRole().equals(ROLE_OWNER)) {
                setVisibleRole(true);
            }
        } else {
            setVisibleRole(false);
        }
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }
}
