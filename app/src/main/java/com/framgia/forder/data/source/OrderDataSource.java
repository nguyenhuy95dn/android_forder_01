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

        Observable<OrderResponse> acceptAndRejectInOrder(int shopId,
                OrderManagerRequest acceptProductInOrderRequest);

        Observable<List<Order>> notifyDoneOrderToServer(int shopId);
    }
}
