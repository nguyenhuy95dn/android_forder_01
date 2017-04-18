package com.framgia.forder.screen.profilepage.updateprofile;

import android.text.TextUtils;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.UserRepository;
import java.util.Objects;

/**
 * Listens to user actions from the UI ({@link UpdateProfileFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class UpdateProfilePresenter implements UpdateProfileContract.Presenter {
    private static final String TAG = UpdateProfilePresenter.class.getName();

    private final UpdateProfileContract.ViewModel mViewModel;
    private UserRepository mUserRepository;
    private User mUser;

    public UpdateProfilePresenter(UpdateProfileContract.ViewModel viewModel,
            UserRepository userRepository) {
        mViewModel = viewModel;
        mUserRepository = userRepository;
    }

    @Override
    public void onStart() {
        mUser = mUserRepository.getUser();
        mViewModel.onGetUserProfile(mUser);
    }

    @Override
    public void onStop() {
    }

    @Override
    public void updateProfile(String newPassword, String chatWorkId, String description,
            String currentPassword) {
        if (!chatWorkId.equals(mUser.getChatworkId())
                || !description.equals(mUser.getDescription())
                || TextUtils.isEmpty(newPassword)) {
            //TODO: Handle update information
        }
    }

    @Override
    public boolean validateDataInput(String newPassword, String confirmNewPassword,
            String currentPassword) {
        boolean isValidate = true;
        if (TextUtils.isEmpty(newPassword)) {
            isValidate = false;
            mViewModel.onInputNewPasswordError();
        }
        if (TextUtils.isEmpty(confirmNewPassword)) {
            isValidate = false;
            mViewModel.onInputConfirmNewPasswordError();
        }
        if (TextUtils.isEmpty(currentPassword)) {
            isValidate = false;
            mViewModel.onInputCurrentPasswordError();
        }
        if (!TextUtils.equals(currentPassword, confirmNewPassword)) {
            isValidate = false;
            mViewModel.onCheckConfirmPasswordError();
        }
        return isValidate;
    }
}
