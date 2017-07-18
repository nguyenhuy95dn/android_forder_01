package com.framgia.forder.screen.profilepage.profiledetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.graphics.Color;
import com.framgia.forder.BR;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.profilepage.updateprofile.UpdateProfileFragment;
import com.framgia.forder.utils.StatusCode;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.utils.StatusCode.ACTIVE_STATUS;
import static com.framgia.forder.utils.StatusCode.BLOCKED_STATUS;
import static com.framgia.forder.utils.StatusCode.WAIT_STATUS;

/**
 * Exposes the data to be used in the ProfileDetail screen.
 */

public class ProfileDetailViewModel extends BaseObservable
        implements ProfileDetailContract.ViewModel {
    private static final String TAG = "UpdateProfileFragment";

    private ProfileDetailContract.Presenter mPresenter;
    private Navigator mNavigator;
    private User mUser;
    private int mStatusColor;

    ProfileDetailViewModel(Navigator navigator) {
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

    public String getChatworkId() {
        return mUser.getChatworkId();
    }

    public String getDescription() {
        return mUser.getDescription();
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            return mUser.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getCreatedAt() {
        return mUser != null ? mUser.getFormatDate() : "";
    }

    public void onClickUpdateProfile() {
        mNavigator.goNextChildFragment(R.id.layout_content, UpdateProfileFragment.newInstance(),
                true, Navigator.RIGHT_LEFT, TAG);
    }

    public void onClickBack() {
        mNavigator.goBackChildFragment();
    }

    @Bindable
    public int getStatusColor() {
        initValueStatus();
        return mStatusColor;
    }

    public String getStatus() {
        return mUser.getStatus();
    }

    private void setStatusColor(int statusColor) {
        mStatusColor = statusColor;
        notifyPropertyChanged(BR.statusColor);
    }

    public void setStatus(String status) {
        switch (status) {
            case ACTIVE_STATUS:
                setStatusColor(Color.parseColor(StatusCode.ACTIVE_STATUS_COLOR));
                break;
            case WAIT_STATUS:
                setStatusColor(Color.parseColor(StatusCode.WAIT_STATUS_COLOR));
                break;
            case BLOCKED_STATUS:
                setStatusColor(Color.parseColor(StatusCode.BLOCKED_STATUS_COLOR));
                break;
            default:
                break;
        }
    }

    private void initValueStatus() {
        if (mUser != null) {
            setStatus(mUser.getStatus());
        }
    }
}
