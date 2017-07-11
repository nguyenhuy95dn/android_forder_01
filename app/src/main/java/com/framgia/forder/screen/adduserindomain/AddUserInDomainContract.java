package com.framgia.forder.screen.adduserindomain;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.AddUserInDomainRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface AddUserInDomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onShowProgressBar();

        void onHideProgressBar();

        void onGetListUserSuccess(List<User> users);

        void onMessageError(BaseException error);

        void onAddUserSuccess();

        void onShowProgressBarDialog();

        void onHideProgressBarDialog();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListUser(int domainId);

        void addUser(AddUserInDomainRequest addUserInDomainRequest);
    }
}
