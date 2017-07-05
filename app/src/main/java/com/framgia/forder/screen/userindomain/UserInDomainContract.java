package com.framgia.forder.screen.userindomain;

import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface UserInDomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {

        void onGetListUserInDomainSuccess(List<User> users, int currentUserId);

        void onGetListUserInDomainError(BaseException error);

        void onDeleteUserInDomainSuccess();

        void onDeleteUserInDomainError(BaseException error);

        void onChangeRoleOfUserSuccess();

        void onChangeRoleOfUserError(BaseException error);

        void onShowProgressDialog();

        void onHideProgressDialog();

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {

        void getListUserInDomain(int domainId);

        void deleteUserInDomain(int domainId, int userId);

        void changeRuleOfUserInDomain(int domainId, int userId, String role);
    }
}
