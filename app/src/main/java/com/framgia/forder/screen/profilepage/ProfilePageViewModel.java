package com.framgia.forder.screen.profilepage;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.login.LoginActivity;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileFragment screen.
 */

public class ProfilePageViewModel implements ProfilePageContract.ViewModel {

    private Navigator mNavigator;
    private ProfilePageContract.Presenter mPresenter;
    private User mUser;

    public ProfilePageViewModel(Navigator navigator) {
        mNavigator = navigator;
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
    public void setPresenter(ProfilePageContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetProfileUserSuccess(User user) {
        if (user == null) {
            return;
        }
        mUser = user;
    }

    public String getUsername() {
        if (mUser == null) {
            return "";
        }
        return mUser.getName();
    }

    public String getEmail() {
        if (mUser == null) {
            return "";
        }
        return mUser.getEmail();
    }

    public void onClickUpdateProfile() {
        //TODO: open Update Profile
    }

    public void onClickHistoryOrder() {
        //TODO: open History Order
    }

    public void onClickYourOrder() {
        //TODO: open Your Order
    }

    public void onClickShopManagement() {
        //TODO: open Shop Management
    }

    public void onClickDomainManagement() {
        //TODO: open Domain Management
    }

    public void onLogoutClicked() {
        mPresenter.clearData();
        mNavigator.startActivity(LoginActivity.class);
        mNavigator.finishActivity();
    }
}
