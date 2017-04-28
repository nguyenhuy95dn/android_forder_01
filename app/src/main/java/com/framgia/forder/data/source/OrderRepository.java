package com.framgia.forder.data.source;

import com.framgia.forder.data.model.CollectionImage;
import com.framgia.forder.data.model.Image;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.model.Product;
import com.framgia.forder.data.model.User;
import com.framgia.forder.data.source.remote.OrderRemoteDataSource;
import java.util.ArrayList;
import java.util.List;
import rx.Observable;

/**
 * Created by Duong on 4/27/2017.
 */

public class OrderRepository {
    private OrderDataSource.RemoteDataSource mRemoteDataSource;

    public OrderRepository(OrderRemoteDataSource orderRemoteDataSource) {
        mRemoteDataSource = orderRemoteDataSource;
    }

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
}
