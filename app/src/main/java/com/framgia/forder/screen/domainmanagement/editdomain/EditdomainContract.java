package com.framgia.forder.screen.domainmanagement.editdomain;

import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface EditdomainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onRequestEditDomain();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
    }
}
