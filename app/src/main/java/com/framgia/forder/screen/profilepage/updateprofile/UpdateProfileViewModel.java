package com.framgia.forder.screen.profilepage.updateprofile;

import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.profilepage.ProfilePageFragment;
import com.framgia.forder.screen.profilepage.profiledetail.ProfileDetailFragment;
import com.framgia.forder.utils.navigator.Navigator;

/**
 * Exposes the data to be used in the ProfileUpdate screen.
 */

public class UpdateProfileViewModel implements UpdateProfileContract.ViewModel {
    private static final String TAG = "ProfileDetailFragment";

    private UpdateProfileContract.Presenter mPresenter;
    private Navigator mNavigator;
    private User mUser;
    private String mEmail;
    private String mUsername;
    private String mNewPassword;
    private String mConfirmNewPassword;
    private String mChatworkId;
    private String mDescription;
    private String mCurrentPassword;

    public UpdateProfileViewModel(Navigator navigator) {
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
    public void setPresenter(UpdateProfileContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetUserProfile(User user) {
        if (user == null) {
            return;
        }
        mUser = user;
    }

    public void onUpdateProfileClick() {
        if (!mPresenter.validateDataInput(mNewPassword, mConfirmNewPassword, mCurrentPassword)) {
            return;
        }
        mPresenter.updateProfile(mNewPassword, mChatworkId, mDescription, mCurrentPassword);
    }

    public void onClickBack() {
        mNavigator.goNextChildFragment(R.id.layout_content, ProfileDetailFragment.newInstance(),
                true, Navigator.FADED, TAG);
    }

    @Override
    public void onUpdateProfileSuccess() {
        //TODO: send success messenger
    }

    @Override
    public void onInputNewPasswordError() {
        //TODO: Alert new password is empty
    }

    @Override
    public void onInputConfirmNewPasswordError() {
        //TODO: Alert confirm new password is empty
    }

    @Override
    public void onInputCurrentPasswordError() {
        //TODO: Alert current password is empty
    }

    @Override
    public void onCheckConfirmPasswordError() {
        //TODO: Alert confirm password does not match
    }

    public String getEmail() {
        return mUser != null ? mUser.getEmail() : "";
    }

    public void setEmail(String email) {
        mEmail = email;
    }

    public String getUsername() {
        return mUser != null ? mUser.getName() : "";
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getNewPassword() {
        return mNewPassword;
    }

    public void setNewPassword(String newPassword) {
        mNewPassword = newPassword;
    }

    public String getConfirmNewPassword() {
        return mConfirmNewPassword;
    }

    public void setConfirmNewPassword(String confirmNewPassword) {
        mConfirmNewPassword = confirmNewPassword;
    }

    public String getChatworkId() {
        return mUser != null ? mUser.getChatworkId() : "";
    }

    public void setChatworkId(String chatworkId) {
        mChatworkId = chatworkId;
    }

    public String getDescription() {
        return mUser != null ? mUser.getDescription() : "";
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public String getCurrentPassword() {
        return mCurrentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        mCurrentPassword = currentPassword;
    }
}
