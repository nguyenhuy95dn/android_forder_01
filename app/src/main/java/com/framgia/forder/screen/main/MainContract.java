package com.framgia.forder.screen.main;

import android.view.View;
import com.framgia.forder.data.model.Cart;
import com.framgia.forder.data.model.Domain;
import com.framgia.forder.data.model.Notification;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.BasePresenter;
import com.framgia.forder.screen.BaseViewModel;
import java.util.List;

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

        void onGetListDomainSuccess(List<Domain> domains);

        void onGetListDomainError(BaseException e);

        void onGetListCartSuccess(List<Cart> carts);

        void onGetListCartError(BaseException error);

        void onGetListNotificationSuccess(List<Notification> notifications);

        void onGetListNotificationError(BaseException exception);

        void reloadData(View view);

        void onReloadCart();

        void onReloadNotification();

        void readAllNotificationSuccess();

        void readAllNotificationError(BaseException exception);

        void onLoadOrderHistoryPage(View viewProfile);
    }

    /**
     * Presenter.
     */
    interface Presenter extends BasePresenter {
        void getCurrentDomain();

        void getListDomain();

        int getCurrentDomainPosition(List<Domain> domains);

        void getListCart();

        void saveCurrentDomain(Domain domain);

        void getListNotification();

        void readAllNotification();
    }
}
