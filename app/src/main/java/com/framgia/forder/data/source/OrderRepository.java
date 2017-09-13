package com.framgia.forder.data.source;

import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import com.framgia.forder.data.source.remote.api.response.BaseResponse;
import com.framgia.forder.data.source.remote.api.response.OrderHistoryShopResponse;
import com.framgia.forder.data.source.remote.api.response.OrderManagerShopReponse;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/27/2017.
 */

public class OrderRepository {
    private final OrderDataSource.RemoteDataSource mRemoteDataSource;

    public OrderRepository(OrderRemoteDataSource orderRemoteDataSource) {
        mRemoteDataSource = orderRemoteDataSource;
    }

    //Todo edit later
    public Observable<List<Order>> getOrderManagement() {
        // todo edit later
        List<Order> orders = new ArrayList<>();
        Order order = new Order();
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        Product product = new Product(1, "Mỳ que", 20000, "",
                new CollectionImage(new Image("https://i.ytimg.com/vi/ToZDdlqjKPE/hqdefault.jpg")),
                null, null, null, null, 1);
        orderDetail.setId(1);
        orderDetail.setStatus(0);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(3);

        orderDetails.add(orderDetail);
        orderDetails.add(orderDetail);

        order.setStatus(0);
        order.setId(1);
        order.setOrderDetails(orderDetails);
        order.setTotalPay(40000);
        order.setUser(new User(1, "Trần văn B", "abc"));
        order.setEndDate("27/04/2017");

        orders.add(order);
        orders.add(order);
        orders.add(order);
        return Observable.just(orders);
    }

    public Observable<List<Order>> getOrderHistory() {
        return mRemoteDataSource.getOrderHistory();
    }

    public Observable<List<Order>> getOrderHistoryByDate(String startDate, String endDate) {
        return mRemoteDataSource.getOrderHistoryByDate(startDate, endDate);
    }

    public Observable<List<Order>> getListOrderManagementShop(int shopId) {
        return mRemoteDataSource.getListOrderManagementShop(shopId);
    }

    public Observable<List<Order>> getListOrderManagementShopFilter(int shopId, String userSearch,
            String domainId) {
        return mRemoteDataSource.getListOrderManagementShopFilter(shopId, userSearch, domainId);
    }

    public Observable<OrderManagerShopReponse> acceptAndRejectInOrder(int shopId,
            OrderManagement acceptProductInOrderRequest) {
        return mRemoteDataSource.acceptAndRejectInOrder(shopId, acceptProductInOrderRequest);
    }

    public Observable<Void> notifyDoneOrderToServer(int shopId) {
        return mRemoteDataSource.notifyDoneOrderToServer(shopId);
    }

    public Observable<List<OrderHistory>> getListOrderHistoryShop(int shopId) {
        return mRemoteDataSource.getListDoneOrdersShop(shopId);
    }

    public Observable<List<OrderHistory>> getListRejectOrdersShop(int shopId) {
        return mRemoteDataSource.getListRejectOrdersShop(shopId);
    }

    public Observable<OrderHistoryShopResponse> getListOrdersShopFilter(int shopId,
            String startDate, String endDate) {
        return mRemoteDataSource.getListOrdersShopFilter(shopId, startDate, endDate);
    }

    public Observable<BaseResponse> requestPaymentOrder(int orderId, boolean paid) {
        return mRemoteDataSource.requestPaymentOrder(orderId, paid);
    }
}
