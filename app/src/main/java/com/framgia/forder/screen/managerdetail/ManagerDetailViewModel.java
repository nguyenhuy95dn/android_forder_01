package com.framgia.forder.screen.managerdetail;

import com.framgia.forder.data.model.User;

/**
 * Exposes the data to be used in the ManagerDetail screen.
 */

public class ManagerDetailViewModel implements ManagerDetailContract.ViewModel {

    private ManagerDetailContract.Presenter mPresenter;
    private final User mUser;

    ManagerDetailViewModel(User user) {
        mUser = user;
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
        if (mUser != null) {
            return mUser.getName();
        }
        return "";
    }

    public String getEmail() {
        if (mUser != null) {
            return mUser.getEmail();
        }
        return "";
    }

    public String getChatWorkId() {
        if (mUser != null) {
            return mUser.getChatworkId();
        }
        return "";
    }

    public String getJoinDate() {
        if (mUser != null) {
            return mUser.getFormatDate();
        }
        return "";
    }

    public String getDescription() {
        if (mUser != null) {
            return mUser.getDescription();
        }
        return "";
    }

    public String getRole() {
        if (mUser != null) {
            return mUser.getRole();
        }
        return "";
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            return mUser.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public boolean isWaitStatus() {
        return mUser.getStatus() == ManagerStatusCode.WAIT_CODE;
    }

    public boolean isActiveStatus() {
        return mUser.getStatus() == ManagerStatusCode.ACTIVE_CODE;
    }

    public boolean isBlockedStatus() {
        return mUser.getStatus() == ManagerStatusCode.BLOCKED_CODE;
    }
}
