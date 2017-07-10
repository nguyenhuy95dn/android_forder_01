package com.framgia.forder.screen.profilepage.updateprofile;

import android.content.Context;
import android.content.Intent;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.net.Uri;
import android.util.Log;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.UpdateProfileRequest;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.io.FileNotFoundException;

import static com.framgia.forder.screen.createProduct.CreateProductFragment.REQUEST_SELECT_IMAGE;

/**
 * Exposes the data to be used in the ProfileUpdate screen.
 */

public class UpdateProfileViewModel extends BaseObservable
        implements UpdateProfileContract.ViewModel {
    private static final String TAG = "ProfileDetailFragment";

    private UpdateProfileContract.Presenter mPresenter;
    private final Context mContext;
    private final Navigator mNavigator;
    private final Navigator mNavigatorForStartGallery;
    private final DialogManager mDialogManager;
    private boolean isClickChooseImage;
    private User mUser;
    private String mEmail;
    private String mUsername;
    private String mChatworkId;
    private String mDescription;
    private String mAvatar;

    UpdateProfileViewModel(Context context, Navigator navigator, Navigator navigatorForStartGallery,
            DialogManager dialogManager) {
        mContext = context;
        mNavigator = navigator;
        mNavigatorForStartGallery = navigatorForStartGallery;
        mDialogManager = dialogManager;
        isClickChooseImage = true;
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
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            mAvatar = mUser.getAvatar().getImage().getUrl();
        } else {
            mAvatar = "";
        }
    }

    public void onUpdateProfileClick() {
        if (!mPresenter.validateDataInputChange(mUsername, mChatworkId, mDescription)) {
            onNothingChange();
        } else {
            try {
                UpdateProfileRequest updateProfileRequest = new UpdateProfileRequest();

                UpdateProfileRequest.UpdateProfileUser updateProfileUser =
                        new UpdateProfileRequest.UpdateProfileUser();
                if (isClickChooseImage) {
                    updateProfileUser.setAvatar(mAvatar);
                } else {
                    updateProfileRequest.setAvatar(
                            mContext.getContentResolver().openInputStream(Uri.parse(mAvatar)));
                }
                updateProfileUser.setName(mUsername);
                updateProfileUser.setChatworkId(mChatworkId);
                updateProfileUser.setDescription(mDescription);

                updateProfileRequest.setUser(updateProfileUser);
                mPresenter.updateProfile(updateProfileRequest, mUsername, mChatworkId,
                        mDescription);
            } catch (FileNotFoundException e) {
                Log.e(TAG, "", e);
            }
        }
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }

    public void onChangePassword() {
        //TODO: navigate to change password fragment
    }

    public void onNothingChange() {
        mNavigator.showToast(R.string.nothing_change);
    }

    @Override
    public void onUpdateProfileSuccess() {
        mNavigator.showToast(R.string.update_profile_success);
        mPresenter.getUserProfile();
    }

    @Override
    public void onUpdateProfileError(BaseException e) {
        mNavigator.showToast(e.getMessage());
    }

    @Override
    public void setAvatar(String avatar) {
        mAvatar = avatar;
        notifyPropertyChanged(BR.avatar);
        isClickChooseImage = false;
    }

    @Override
    public void onShowProgressDialog() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressDialog() {
        mDialogManager.dismissProgressDialog();
    }

    @Bindable
    public String getAvatar() {
        return mAvatar;
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

    public void onClickChooseAvatar() {
        Intent intent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        mNavigatorForStartGallery.startActivityForResultFromFragment(intent, REQUEST_SELECT_IMAGE);
    }
}
