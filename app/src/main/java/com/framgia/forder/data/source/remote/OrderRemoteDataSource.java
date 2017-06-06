package com.framgia.forder.data.source.remote;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.OrderDataSource;
import com.framgia.forder.data.source.remote.api.request.OrderManagerRequest;
import com.framgia.forder.data.source.remote.api.response.OrderManagementResponse;
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
    public Observable<List<Order>> getOrderHistory(int userId, int domainId) {
        return mFOrderApi.getListOrder(userId, domainId)
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
    public Observable<OrderResponse> acceptProductInOrder(int shopId,
            OrderManagerRequest acceptProductInOrderRequest) {
        return mFOrderApi.acceptProductInOrder(shopId, acceptProductInOrderRequest);
    }

    @Override
    public Observable<OrderResponse> rejectProductInOrder(int shopId,
            OrderManagerRequest rejectProductInOrderRequest) {
        return mFOrderApi.rejectProductInOrder(shopId, rejectProductInOrderRequest);
    }

    @Override
    public Observable<OrderResponse> acceptAllProductInOrder(int shopId,
            OrderManagerRequest acceptAllProductInOrderRequest) {
        return mFOrderApi.acceptAllProductInOrder(shopId, acceptAllProductInOrderRequest);
    }

    @Override
    public Observable<OrderResponse> rejectAllProductInOrder(int shopId,
            OrderManagerRequest rejectAllProductInOrderRequest) {
        return mFOrderApi.rejectAllProductInOrder(shopId, rejectAllProductInOrderRequest);
    }

    @Override
    public Observable<OrderResponse> acceptAllOrder(int shopId,
            OrderManagerRequest acceptAllOrderRequest) {
        return mFOrderApi.acceptAllOrder(shopId, acceptAllOrderRequest);
    }

    @Override
    public Observable<OrderResponse> rejectAllOrder(int shopId,
            OrderManagerRequest rejectAllOrderRequest) {
        return mFOrderApi.rejectAllOrder(shopId, rejectAllOrderRequest);
    }

    @Override
    public Observable<List<Order>> notifyDoneOrderToServer(int shopId) {
        return mFOrderApi.notifyDoneOrderToServer(shopId)
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
}
