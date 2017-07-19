package com.framgia.forder.screen.addmanagershop;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AddManagerShopContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListUserSuccess(List<User> userList);

        void onAddManagerInShopSuccess();

        void onMessageError(BaseException exception);

        void onShowProgressBar();

        void onHideProgressBar();

        void onShowProgressDialog();

        void onHideProgressDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListUser();

        void addManagerInShop(int shopId, int userId);
    }
}
