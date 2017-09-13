package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.source.OrderDataSource;
import com.framgia.forder.data.source.remote.api.response.CloseOrderResponse;
import com.framgia.forder.data.source.remote.api.response.OrderHistoryShopResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagementResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagerShopReponse;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import com.framgia.forder.data.source.remote.api.service.FOrderApi;
import java.util.List;
import rx.Observable;
import rx.functions.Func1;

/**
 * Created by Duong on 4/27/2017.
 */

public class OrderRemoteDataSource extends BaseRemoteDataSource
        implements OrderDataSource.RemoteDataSource {

    public OrderRemoteDataSource(FOrderApi api) {
        super(api);
    }

    @Override
    public Observable<List<Order>> getOrderManagement() {
        return mFOrderApi.getOrderManagement()
                .flatMap(new Func1<OrderManagementResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(
                            OrderManagementResponse orderManagementResponse) {
                        if (orderManagementResponse != null) {
                            return Observable.just(orderManagementResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Order>> getOrderHistory() {
        return mFOrderApi.getListOrderHistoryToday()
                .flatMap(new Func1<OrderResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(OrderResponse orderResponse) {
                        if (orderResponse != null) {
                            return Observable.just(orderResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Order>> getOrderHistoryByDate(String startDate, String endDate) {
        return mFOrderApi.getListOrderHistoryByDate(startDate, endDate)
                .flatMap(new Func1<OrderResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(OrderResponse orderResponse) {
                        if (orderResponse != null) {
                            return Observable.just(orderResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Order>> getListOrderManagementShop(int shopId) {
        return mFOrderApi.getListOrderManagementShop(shopId)
                .flatMap(new Func1<OrderManagementResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(
                            OrderManagementResponse orderManagementResponse) {
                        if (orderManagementResponse != null) {
                            return Observable.just(orderManagementResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<Order>> getListOrderManagementShopFilter(int shopId, String userSearch,
            String domainId) {
        return mFOrderApi.getListOrderManagementShopFilter(shopId, userSearch, domainId)
                .flatMap(new Func1<OrderManagementResponse, Observable<List<Order>>>() {
                    @Override
                    public Observable<List<Order>> call(
                            OrderManagementResponse orderManagementResponse) {
                        if (orderManagementResponse != null) {
                            return Observable.just(orderManagementResponse.getOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<OrderManagerShopReponse> acceptAndRejectInOrder(int shopId,
            OrderManagement acceptProductInOrderRequest) {
        return mFOrderApi.acceptAndRejectInOrder(shopId, acceptProductInOrderRequest);
    }

    @Override
    public Observable<Void> notifyDoneOrderToServer(int shopId) {
        return mFOrderApi.notifyDoneOrderToServer(shopId)
                .flatMap(new Func1<CloseOrderResponse, Observable<Void>>() {
                    @Override
                    public Observable<Void> call(CloseOrderResponse closeOrderResponse) {
                        return Observable.just(null);
                    }
                });
    }

    @Override
    public Observable<List<OrderHistory>> getListDoneOrdersShop(int shopId) {
        return mFOrderApi.getListOrderHistoryShop(shopId)
                .flatMap(new Func1<OrderHistoryShopResponse, Observable<List<OrderHistory>>>() {
                    @Override
                    public Observable<List<OrderHistory>> call(
                            OrderHistoryShopResponse orderHistoryShopResponses) {
                        if (orderHistoryShopResponses != null) {
                            return Observable.just(orderHistoryShopResponses.getOrderHistoryList()
                                    .getListDoneOrders());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<List<OrderHistory>> getListRejectOrdersShop(int shopId) {
        return mFOrderApi.getListOrderHistoryShop(shopId)
                .flatMap(new Func1<OrderHistoryShopResponse, Observable<List<OrderHistory>>>() {
                    @Override
                    public Observable<List<OrderHistory>> call(
                            OrderHistoryShopResponse orderHistoryShopResponses) {
                        if (orderHistoryShopResponses != null) {
                            return Observable.just(orderHistoryShopResponses.getOrderHistoryList()
                                    .getListRejectedOrder());
                        }
                        return Observable.error(new NullPointerException());
                    }
                });
    }

    @Override
    public Observable<OrderHistoryShopResponse> getListOrdersShopFilter(int shopId,
            String startDate, String endDate) {
        return mFOrderApi.getListOrderHistoryShopFilter(shopId, startDate, endDate);
    }
}
