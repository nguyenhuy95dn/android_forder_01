package com.framgia.forder.screen.orderhistoryshop;

import com.framgia.forder.data.source.OrderRepository;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.data.source.remote.api.error.SafetyError;
import com.framgia.forder.data.source.remote.api.response.OrderHistoryShopResponse;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Listens to user actions from the UI ({@link OrderHistoryShopFragment}), retrieves the data and
 * updates
 * the UI as required.
 */
final class OrderHistoryShopPresenter implements OrderHistoryShopContract.Presenter {
    private static final String TAG = OrderHistoryShopPresenter.class.getName();

    private final OrderHistoryShopContract.ViewModel mViewModel;
    private final OrderRepository mOrderRepository;
    private final CompositeSubscription mCompositeSubscription;

    OrderHistoryShopPresenter(OrderHistoryShopContract.ViewModel viewModel,
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
    public void getListOrdersShopFilter(int shopId, String startDate, String endDate) {
        Subscription subscription =
                mOrderRepository.getListOrdersShopFilter(shopId, startDate, endDate)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<OrderHistoryShopResponse>() {
                            @Override
                            public void call(OrderHistoryShopResponse orderHistoryShopResponse) {
                                if (orderHistoryShopResponse == null
                                        && orderHistoryShopResponse.getOrderHistoryList() == null) {
                                    return;
                                }
                                mViewModel.onFilterListDoneOrderSuccess(
                                        orderHistoryShopResponse.getOrderHistoryList()
                                                .getListDoneOrders());
                                mViewModel.onFilterListRejectOrdeSuccess(
                                        orderHistoryShopResponse.getOrderHistoryList()
                                                .getListRejectedOrder());
                            }
                        }, new SafetyError() {
                            @Override
                            public void onSafetyError(BaseException error) {
                                mViewModel.onFilterListOrderError(error);
                            }
                        });
        mCompositeSubscription.add(subscription);
    }
}
