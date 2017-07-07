package com.framgia.forder.screen.listacceptorder;

import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;

/**
 * This specifies the contract between the view and the presenter.
 */
interface ListAcceptOrderContract {
    /**
     * View.
     */
    interface ViewModel extends BaseViewModel<Presenter> {
        void onOrderSuccess();

        void onOrderError(BaseException error);

        void onShowProgressBar();

        void onHideProgressBar();
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void notifyDoneOrderToServer(int shopId);
    }
}
