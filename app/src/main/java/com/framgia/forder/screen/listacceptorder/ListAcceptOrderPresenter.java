package com.framgia.forder.screen.listacceptorder;

import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action0;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link ListAcceptOrderFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class ListAcceptOrderPresenter implements ListAcceptOrderContract.Presenter {
    private static final String TAG = ListAcceptOrderPresenter.class.getName();

    private final ListAcceptOrderContract.ViewModel mViewModel;
    private final OrderRepository mOrderRepository;
    private final CompositeSubscription mCompositeSubscription;

    ListAcceptOrderPresenter(ListAcceptOrderContract.ViewModel viewModel,
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
    public void notifyDoneOrderToServer(int shopId) {
        Subscription subscription = mOrderRepository.notifyDoneOrderToServer(shopId)
                .subscribeOn(Schedulers.io())
                .doOnSubscribe(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onShowProgressBar();
                    }
                })
                .doAfterTerminate(new Action0() {
                    @Override
                    public void call() {
                        mViewModel.onHideProgressBar();
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mViewModel.onOrderSuccess();
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onOrderError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
