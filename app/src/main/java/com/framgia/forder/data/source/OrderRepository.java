package com.framgia.forder.data.source;

import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
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

    //Todo edit later
    public Observable<List<Order>> getOrderHistory(int userId, int domainId) {
        // TODO: Fake data, remove late
        String productImage = "http://www.papawestray.co.uk/images/shop-interior.jpg";
        List<Order> orders = new ArrayList<>();
        List<OrderDetail> orderDetails = new ArrayList<>();
        OrderDetail orderDetail = new OrderDetail();
        int status = 1;
        Double totalPay = 120000.0;
        String time = "2017-04-27T00:00:00.000Z";
        for (int i = 0; i < 3; i++) {
            Product product = new Product(1, "Cơm rang", 12000.0, "Ngon",
                    new CollectionImage(new Image(productImage)), null, null, "accepted", null, 1);
            orderDetail.setId(1);
            orderDetail.setStatus(1);
            orderDetail.setQuantity(10);
            orderDetail.setProduct(product);
            orderDetails.add(orderDetail);
            Order order = new Order();
            order.setId(1);
            order.setStatus(status);
            order.setEndDate(time);
            order.setTotalPay(totalPay);
            order.setOrderDetails(orderDetails);
            orders.add(order);
        }
        return Observable.just(orders);
    }

    public Observable<List<Order>> getListOrderManagementShop(int shopId) {
        return mRemoteDataSource.getListOrderManagementShop(shopId);
    }

    public Observable<OrderManagerShopReponse> acceptAndRejectInOrder(int shopId,
            OrderManagement acceptProductInOrderRequest) {
        return mRemoteDataSource.acceptAndRejectInOrder(shopId, acceptProductInOrderRequest);
    }

    public Observable<List<Order>> notifyDoneOrderToServer(int shopId) {
        return mRemoteDataSource.notifyDoneOrderToServer(shopId);
    }

}
