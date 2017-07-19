package com.framgia.forder.screen.addmanagershop;

import com.framgia.forder.data.model.User;

/**
 * Created by levutantuan on 7/18/17.
 */

public class ItemAddManagerInShopViewModel {
    private User mUser;

    public ItemAddManagerInShopViewModel(User user) {
        mUser = user;
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
}
