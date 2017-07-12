package com.framgia.forder.screen.userindomain;

import android.databinding.BaseObservable;
import android.view.MenuItem;
import com.framgia.forder.R;
import com.framgia.forder.data.model.User;

/**
 * Created by nguyenhuy95dn on 6/26/2017.
 */

public class ItemUserInDomainViewModel extends BaseObservable {

    private final User mUser;
    private final UserInDomainListener mUserInDomainListener;
    private boolean mIsAuthority;
    private boolean mIsMember;

    ItemUserInDomainViewModel(User user, UserInDomainListener userInDomainListener,
            boolean isAuthority) {
        mUser = user;
        mUserInDomainListener = userInDomainListener;
        mIsAuthority = isAuthority;
        checkRole();
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null) {
            return mUser.getAvatar().getUrl();
        }
        return "";
    }

    private void checkRole() {
        if (isCheckOwner()) {
            mIsAuthority = false;
        }
        if (isCheckManager()) {
            mIsMember = false;
        }
        if (isCheckMember()) {
            mIsMember = true;
        }
    }

    public boolean isCheckMember() {
        if (mIsAuthority && mUser.getRoleOfMember() == 2) {
            return true;
        }
        return false;
    }

    public boolean isCheckManager() {
        if (mIsAuthority && mUser.getRoleOfMember() == 1) {
            return true;
        }
        return false;
    }

    public boolean isCheckOwner() {
        if (mIsAuthority && mUser.getRoleOfMember() == 0) {
            return true;
        }
        return false;
    }

    public String getName() {
        return mUser.getName();
    }

    public String getEmail() {
        return mUser.getEmail();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mUserInDomainListener.onClickDeleteUser(mUser.getId());
                return true;
            case R.id.action_set_manager:
                mUserInDomainListener.onClickAddManager(mUser.getId());
                return true;
            default:
        }
        return false;
    }

    public boolean onMenuItemClickCancelManager(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_delete:
                mUserInDomainListener.onClickDeleteUser(mUser.getId());
                return true;
            case R.id.action_cancel_manager:
                mUserInDomainListener.onClickCancelManager(mUser.getId());
                return true;
            default:
        }
        return false;
    }

    public boolean isMember() {
        return mIsMember;
    }

    public boolean isManager() {
        return !mIsMember;
    }

    public boolean isAuthority() {
        return mIsAuthority;
    }
}
