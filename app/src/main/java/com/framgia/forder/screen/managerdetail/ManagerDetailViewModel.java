package com.framgia.forder.screen.managerdetail;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.data.source.remote.api.response.ManagerResponse;
import com.framgia.forder.utils.navigator.Navigator;

import static com.framgia.forder.utils.StatusCode.ROLE_OWNER;

/**
 * Exposes the data to be used in the ManagerDetail screen.
 */

public class ManagerDetailViewModel extends BaseObservable
        implements ManagerDetailContract.ViewModel {

    private ManagerDetailContract.Presenter mPresenter;
    private final Navigator mNavigator;
    private final ManagerResponse.ManagerDetail mManagerDetail;
    private boolean mIsVisibleRole;

    ManagerDetailViewModel(Navigator navigator, ManagerResponse.ManagerDetail managerDetail) {
        mNavigator = navigator;
        mManagerDetail = managerDetail;
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
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getName();
        }
        return "";
    }

    public String getEmail() {
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getEmail();
        }
        return "";
    }

    public String getChatWorkId() {
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getChatworkId();
        }
        return "";
    }

    public String getJoinDate() {
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getFormatDate();
        }
        return "";
    }

    public String getDescription() {
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getDescription();
        }
        return "";
    }

    public String getRole() {
        return mManagerDetail.getRole();
    }

    public String getAvatar() {
        if (mManagerDetail.getUser() != null
                && mManagerDetail.getUser().getAvatar() != null
                && mManagerDetail.getUser().getAvatar().getImage() != null) {
            return mManagerDetail.getUser().getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getStatus() {
        if (mManagerDetail.getUser() != null) {
            return mManagerDetail.getUser().getStatus();
        }
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
        if (mManagerDetail != null) {
            if (mManagerDetail.getRole().equals(ROLE_OWNER)) {
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
