package com.framgia.forder.screen.managerinshop;

import android.databinding.BaseObservable;
import com.framgia.forder.data.model.OwnerShop;

/**
 * Created by nguyenhuy95dn on 6/25/2017.
 */

public class ItemManagerInShopViewModel extends BaseObservable {

    private static final String DEFAULT_AVATAR_LINK =
            "http://res.cloudinary.com/hoangmirs/image/upload/c_fill,g_north,h_100,"
                    + "w_100/tgklvf8yxofaosrpxj0o.png";
    private final OwnerShop mOwnerShop;

    ItemManagerInShopViewModel(OwnerShop ownerShop) {
        mOwnerShop = ownerShop;
    }

    public String getManagerImage() {
        //To do fix later when fix api
        return DEFAULT_AVATAR_LINK;
    }

    public String getName() {
        return mOwnerShop.getUserName();
    }
}
