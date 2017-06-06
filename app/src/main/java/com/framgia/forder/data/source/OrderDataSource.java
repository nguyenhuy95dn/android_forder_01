package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.source.remote.api.request.OrderManagerRequest;
import com.framgia.forder.data.source.remote.api.response.OrderResponse;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/27/2017.
 */

public interface OrderDataSource {
    interface RemoteDataSource {
        Observable<List<Order>> getOrderManagement();

        Observable<List<Order>> getOrderHistory(int userId, int domainId);

        Observable<List<Order>> getListOrderManagementShop(int shopId);

        Observable<OrderResponse> acceptProductInOrder(int shopId,
                OrderManagerRequest acceptProductInOrderRequest);

        Observable<OrderResponse> rejectProductInOrder(int shopId,
                OrderManagerRequest rejectProductInOrderRequest);

        Observable<OrderResponse> acceptAllProductInOrder(int shopId,
                OrderManagerRequest acceptAllProductInOrderRequest);

        Observable<OrderResponse> rejectAllProductInOrder(int shopId,
                OrderManagerRequest rejectAllProductInOrderRequest);

        Observable<OrderResponse> acceptAllOrder(int shopId,
                OrderManagerRequest acceptAllOrderRequest);

        Observable<OrderResponse> rejectAllOrder(int shopId,
                OrderManagerRequest rejectAllOrderRequest);

        Observable<List<Order>> notifyDoneOrderToServer(int shopId);
    }
}
