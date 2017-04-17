package com.framgia.forder.screen.profilepage.profiledetail;

import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.User;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileDetail screen.
 */

public class ProfileDetailViewModel implements ProfileDetailContract.ViewModel {

    private ProfileDetailContract.Presenter mPresenter;
    private Navigator mNavigator;
    private User mUser;

    public ProfileDetailViewModel(Navigator navigator) {
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
    public void setPresenter(ProfileDetailContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetProfileDetail(User user) {
        mUser = user;
    }

    public String getName() {
        return mUser != null ? mUser.getName() : "";
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public String getCreatedAt() {
        return mUser != null ? mUser.getFormatDate() : "";
    }
}
