package com.framgia.forder.screen.adduserindomain;

import com.framgia.forder.data.model.User;

/**
 * Created by nguyenhuy95dn on 7/11/2017.
 */

public class ItemAddUserInDomainViewModel {

    private final User mUser;
    private final AddUserInDomainAdapter.AddUserInDomainListener mAddUserInDomainListener;

    ItemAddUserInDomainViewModel(User user,
            AddUserInDomainAdapter.AddUserInDomainListener addUserInDomainListener) {
        mUser = user;
        mAddUserInDomainListener = addUserInDomainListener;
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

    public void onClickAddUser() {
        mAddUserInDomainListener.onClickAddUser(mUser.getId());
    }
}
