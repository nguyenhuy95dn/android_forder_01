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

    ItemUserInDomainViewModel(User user, UserInDomainListener userInDomainListener) {
        mUser = user;
        mUserInDomainListener = userInDomainListener;
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null) {
            return mUser.getAvatar().getUrl();
        }
        return "";
    }

    public String getName() {
        return mUser.getName();
    }

    public String getEmail() {
        return mUser.getEmail();
    }

    public boolean onMenuItemClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.delete:
                mUserInDomainListener.onClickDeleteUser(mUser.getId());
                return true;
            case R.id.set_manager:
                mUserInDomainListener.onClickAddManager(mUser.getId());
                return true;
            default:
        }
        return false;
    }
}
