package com.framgia.forder.screen.shopindomain;

import android.content.DialogInterface;
import com.framgia.forder.R;
import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.model.OwnerShop;
import com.framgia.forder.data.model.ShopInDomain;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.List;

/**
 * Exposes the data to be used in the Shopindomain screen.
 */

public class ShopInDomainViewModel implements ShopInDomainContract.ViewModel, ShopInDomainListener {

    private static final String TAG = "ManagerInShopFragment";

    private final ShopInDomainAdapter mAdapter;
    private final Navigator mNavigator;
    private final DomainManagement mDomainManagement;
    private final DialogManager mDialogManager;
    private ShopInDomainContract.Presenter mPresenter;

    ShopInDomainViewModel(ShopInDomainAdapter shopInDomainAdapter, Navigator navigator,
            DomainManagement domainManagement, DialogManager dialogManager) {
        mAdapter = shopInDomainAdapter;
        mNavigator = navigator;
        mDomainManagement = domainManagement;
        mDialogManager = dialogManager;
        shopInDomainAdapter.setShopInDomainListener(this);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListShopInDomain(mDomainManagement.getId());
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ShopInDomainContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ShopInDomainAdapter getAdapter() {
        return mAdapter;
    }

    @Override
    public void onGetListShopInDomainSuccess(List<ShopInDomain> shops, int userId) {
        mAdapter.updateData(shops);
    }

    @Override
    public void onGetListShopInDomainError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void ondeleteShopInDomainSuccess() {
        mPresenter.getListShopInDomain(mDomainManagement.getId());
    }

    @Override
    public void ondeleteShopInDomainError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onClickSeeAllManager(List<OwnerShop> ownerShops) {
        mNavigator.showManagerInShopDialog(TAG, ownerShops);
    }

    @Override
    public void onClickDeleteShop(final ShopInDomain shop) {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.msg_delete_domain,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.deleteShop(mDomainManagement.getId(), shop.getId());
                    }
                });
        mDialogManager.show();
    }
}
