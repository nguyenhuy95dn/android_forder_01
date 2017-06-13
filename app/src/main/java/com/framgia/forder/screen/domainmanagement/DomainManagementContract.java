package com.framgia.forder.screen.domainmanagement;

import com.framgia.forder.data.model.DomainManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.request.RegisterDomainRequest;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

/**
 * This specifies the contract between the view and the presenter.
 */
interface DomainManagementContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onGetListDomainManagementSuccess(List<DomainManagement> domainManagementList);

        void onGetListDomainManagementError(BaseException error);

        void onRegisterDomainSuccess();

        void onRegisterDomainError(BaseException error);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getListDomainManagement();

        void registerDomain(RegisterDomainRequest registerDomainRequest);
    }
}
