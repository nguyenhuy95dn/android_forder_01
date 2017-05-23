package com.framgia.forder.screen.shopinfo;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.User;
import com.framgia.forder.screen.BaseRecyclerViewAdapter;

/**
 * Created by levutantuan on 5/18/17.
 */

public class ItemManagerShopInfoViewModel extends BaseObservable {

    private static final String TAG = "ShopinfoFragment";
    private final User mUser;
    private final BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> mItemClickListener;

    ItemManagerShopInfoViewModel(User user,
            BaseRecyclerViewAdapter.OnRecyclerViewItemClickListener<User> itemClickListener) {
        mUser = user;
        mItemClickListener = itemClickListener;
    }

    public String getAvatar() {
        if (mUser.getAvatar() != null && mUser.getAvatar().getImage() != null) {
            return mUser.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public void onClickManagerDetail() {
        if (mItemClickListener == null) {
            return;
        }
        mItemClickListener.onItemRecyclerViewClick(mUser);
    }
}
