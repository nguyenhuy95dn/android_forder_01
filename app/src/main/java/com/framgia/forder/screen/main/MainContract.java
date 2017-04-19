package com.framgia.forder.screen.main;

import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface MainContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        boolean onBackPressed();

        void showCurrentDomain(String domainName);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getCurrentDomain();
    }
}
