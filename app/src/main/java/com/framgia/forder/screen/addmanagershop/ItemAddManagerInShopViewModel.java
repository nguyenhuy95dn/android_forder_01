package com.framgia.forder.screen.addmanagershop;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.User;

/**
 * Created by levutantuan on 7/18/17.
 */

public class ItemAddManagerInShopViewModel extends BaseObservable {
    private final User mUser;
    private boolean mIsVisibleAddManager;
    private final int mUserId;

    public ItemAddManagerInShopViewModel(User user, int userId) {
        mUser = user;
        mUserId = userId;
        initValueCheckUser();
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

    @Bindable
    public boolean isVisibleAddManager() {
        return mIsVisibleAddManager;
    }

    private void setVisibleAddManager(boolean visibleAddManager) {
        mIsVisibleAddManager = visibleAddManager;
        notifyPropertyChanged(BR.visibleAddManager);
    }

    public void onClickAddManager() {
        //Todo edit later
    }

    private void initValueCheckUser() {
        if (mUser != null) {
            setVisibleAddManager(!(mUser.getId() == mUserId));
        }
    }
}
