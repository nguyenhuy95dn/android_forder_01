package com.framgia.forder.screen.orderhistoryshop.listdoneorders;

import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ListDoneOrdersFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ListDoneOrdersPresenter implements ListDoneOrdersContract.Presenter {
    private static final String TAG = ListDoneOrdersPresenter.class.getName();

    private final ListDoneOrdersContract.ViewModel mViewModel;
    private final OrderRepository mOrderRepository;
    private final CompositeSubscription mCompositeSubscription;

    ListDoneOrdersPresenter(ListDoneOrdersContract.ViewModel viewModel,
            OrderRepository orderRepository) {
        mViewModel = viewModel;
        mOrderRepository = orderRepository;
        mCompositeSubscription = new CompositeSubscription();
    }

    @Override
    public void onStart() {
    }

    @Override
    public void onStop() {
        mCompositeSubscription.clear();
    }

    @Override
    public void getListDoneOrder(int shopId) {
        Subscription subscription = mOrderRepository.getListOrderHistoryShop(shopId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<OrderHistory>>() {
                    @Override
                    public void call(List<OrderHistory> orderHistories) {
                        mViewModel.onGetListDoneOrdersSuccess(orderHistories);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListDoneOrdersError(error);
                    }
                }, new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBar();
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
