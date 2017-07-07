package com.framgia.forder.screen.listacceptorder;

import android.content.DialogInterface;
import com.framgia.forder.R;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import com.framgia.forder.widgets.dialog.DialogManager;

/**
 * Exposes the data to be used in the Listacceptorder screen.
 */

public class ListAcceptOrderViewModel implements ListAcceptOrderContract.ViewModel {

    private final ListAcceptOrderAdapter mAdapter;
    private final Navigator mNavigator;
    private final DialogManager mDialogManager;
    private final int mShopId;
    private ListAcceptOrderContract.Presenter mPresenter;

    public ListAcceptOrderViewModel(ListAcceptOrderAdapter adapter, Navigator navigator,
            DialogManager dialogManager, int shopId) {
        mAdapter = adapter;
        mNavigator = navigator;
        mDialogManager = dialogManager;
        mShopId = shopId;
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(ListAcceptOrderContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public ListAcceptOrderAdapter getListAcceptOrder() {
        return mAdapter;
    }

    @Override
    public void onOrderSuccess() {
        mNavigator.showToastCustomActivity(R.string.close_order_success);
        mNavigator.goBackChildFragment();
    }

    @Override
    public void onOrderError(BaseException error) {
        mNavigator.showToastCustom(error.getMessage());
    }

    @Override
    public void onShowProgressBar() {
        mDialogManager.showProgressDialog();
    }

    @Override
    public void onHideProgressBar() {
        mDialogManager.dismissProgressDialog();
    }

    public void onClickDoneOrder() {
        mDialogManager.dialogwithNoTitleTwoButton(R.string.mgs_accept_order,
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        mPresenter.notifyDoneOrderToServer(mShopId);
                    }
                });
        mDialogManager.show();
    }
}
