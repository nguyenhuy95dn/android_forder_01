package com.framgia.forder.screen.profilepage.updateprofile;

import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UpdateProfileContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetUserProfile(User user);

        void onUpdateProfileSuccess();

        void onInputNewPasswordError();

        void onInputConfirmNewPasswordError();

        void onInputCurrentPasswordError();

        void onCheckConfirmPasswordError();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void updateProfile(String newPassword, String chatWorkId, String description,
                String currentPassword);

        boolean validateDataInput(String newPassword, String confirmNewPassword,
                String currentPassword);
    }
}
