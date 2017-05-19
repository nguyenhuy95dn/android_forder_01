package com.framgia.forder.screen.shopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.User;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ItemManagerShopInfoViewModel extends BaseObservable {
    private final User mUser;

    ItemManagerShopInfoViewModel(User user) {
        mUser = user;
    }

    public String getName() {
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
}
