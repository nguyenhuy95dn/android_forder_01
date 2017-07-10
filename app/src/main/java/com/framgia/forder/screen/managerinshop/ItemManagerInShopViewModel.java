package com.framgia.forder.screen.managerinshop;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.OwnerShop;

/**
 * Created by nguyenhuy95dn on 6/25/2017.
 */

public class ItemManagerInShopViewModel extends BaseObservable {

    private final OwnerShop mOwnerShop;

    ItemManagerInShopViewModel(OwnerShop ownerShop) {
        mOwnerShop = ownerShop;
    }

    public String getManagerImage() {
        if (mOwnerShop.getAvatar() != null && mOwnerShop.getAvatar().getImage() != null) {
            return mOwnerShop.getAvatar().getImage().getUrl();
        }
        return "";
    }

    public String getName() {
        return mOwnerShop.getUserName();
    }
}
