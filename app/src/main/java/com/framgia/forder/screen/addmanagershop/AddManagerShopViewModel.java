package com.framgia.forder.screen.addmanagershop;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.List;

/**
 * Exposes the data to be used in the Addmanagershop screen.
 */

public class AddManagerShopViewModel extends BaseObservable
        implements AddManagerShopContract.ViewModel {

    private AddManagerShopContract.Presenter mPresenter;
    private final AddManagerShopAdapter mAdapter;
    private final Navigator mNavigator;
    private boolean mIsVisibleListUser;

    AddManagerShopViewModel(Navigator navigator, AddManagerShopAdapter adapter) {
        mNavigator = navigator;
        mAdapter = adapter;
        setVisibleListUser(false);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
        mPresenter.getListUser();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(AddManagerShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    @Override
    public void onGetListUserSuccess(List<User> userList, int userId) {
        mAdapter.updateData(userList, userId);
    }

    @Override
    public void onAddManagerInShopSuccess() {
        //Todo edit later
    }

    @Override
    public void onMessageError(BaseException exception) {
        mNavigator.showToastCustom(exception.getMessage());
    }

    @Override
    public void onShowProgressBar() {
        setVisibleListUser(true);
    }

    @Override
    public void onHideProgressBar() {
        setVisibleListUser(false);
    }

    @Override
    public void onShowProgressDialog() {
        //Todo Show Dialog
    }

    @Override
    public void onHideProgressDialog() {
        //Todo Hide Dialog
    }

    public AddManagerShopAdapter getAdapter() {
        return mAdapter;
    }

    @Bindable
    public boolean isVisibleListUser() {
        return mIsVisibleListUser;
    }

    private void setVisibleListUser(boolean visibleListUser) {
        mIsVisibleListUser = visibleListUser;
        notifyPropertyChanged(BR.visibleListUser);
    }
}
