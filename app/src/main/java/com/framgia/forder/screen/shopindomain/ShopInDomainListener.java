package com.framgia.forder.screen.shopindomain;

import com.framgia.forder.data.model.OwnerShop;
import com.framgia.forder.data.model.ShopInDomain;
import java.util.List;

/**
 * Created by Age on 6/21/2017.
 */

public interface ShopInDomainListener {
    void onClickSeeMoreOwner(List<OwnerShop> ownerShops);

    void onClickDeleteShop(ShopInDomain shop);
}
