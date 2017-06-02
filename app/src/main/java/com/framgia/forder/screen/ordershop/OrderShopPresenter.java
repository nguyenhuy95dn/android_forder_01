package com.framgia.forder.screen.ordershop;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import java.util.List;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link OrderShopFragment}), retrieves the data and updates
 * the UI as required.
 */
final class OrderShopPresenter implements OrderShopContract.Presenter {
    private static final String TAG = OrderShopPresenter.class.getName();

    private final OrderShopContract.ViewModel mViewModel;
    private final OrderRepository mOrderRepository;
    private final CompositeSubscription mCompositeSubscription;

    OrderShopPresenter(OrderShopContract.ViewModel viewModel, OrderRepository orderRepository) {
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
    public void onGetListOrderManagementShop(int shopId) {
        Subscription subscription = mOrderRepository.getListOrderManagementShop(shopId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<List<Order>>() {
                    @Override
                    public void call(List<Order> orders) {
                        mViewModel.onGetListOrderManagementShopSuccess(orders);
                    }
                }, new SafetyError() {
                    @Override
                    public void onSafetyError(BaseException error) {
                        mViewModel.onGetListOrderManagementShopError(error);
                    }
                });
        mCompositeSubscription.add(subscription);
    }
}
