package com.framgia.forder.data.source;

import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderHistoryList;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.source.remote.api.response.OrderManagerShopReponse;
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

        Observable<OrderManagerShopReponse> acceptAndRejectInOrder(int shopId,
                OrderManagement acceptProductInOrderRequest);

        Observable<Void> notifyDoneOrderToServer(int shopId);

        Observable<List<OrderHistoryList>> getListOrderHistoryShop(int shopId);
    }
}
