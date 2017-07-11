package com.framgia.forder.screen.ordershop;

import android.content.Context;
import android.databinding.ObservableField;
import android.view.View;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Order;
import com.framgia.forder.data.model.OrderDetail;
import com.framgia.forder.data.model.OrderManagement;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.listacceptorder.ListAcceptOrderFragment;
import com.framgia.forder.utils.navigator.Navigator;
import java.util.ArrayList;
import java.util.List;

import static com.framgia.forder.data.model.OrderDetail.StatusOders.Accepted;
import static com.framgia.forder.data.model.OrderDetail.StatusOders.Pending;

/**
 * Exposes the data to be used in the OrderShop screen.
 */

public class OrderShopViewModel
        implements OrderShopContract.ViewModel, OrderShopAdapter.OrderManagementListener {

    private final Context mContext;
    private final Navigator mNavigator;
    private OrderShopContract.Presenter mPresenter;
    private final OrderShopAdapter mOrderShopAdapter;
    private final ShopManagement mShopManagement;
    private final int mShopId;
    private List<Order> mOrders;
    private final ObservableField<Integer> mProgressBarVisibilityListOrder =
            new ObservableField<>();

    OrderShopViewModel(Context context, Navigator navigator, OrderShopAdapter orderShopAdapter,
            ShopManagement shopManagement) {
        mContext = context;
        mNavigator = navigator;
        mOrderShopAdapter = orderShopAdapter;
        mShopManagement = shopManagement;
        mOrderShopAdapter.setOrderManagementListener(this);
        mShopId = mShopManagement.getShop().getId();
        mOrders = new ArrayList<>();
        mProgressBarVisibilityListOrder.set(View.GONE);
    }

    @Override
    public void onStart() {
        mPresenter.onStart();
    }

    @Override
    public void onStop() {
        mPresenter.onStop();
    }

    @Override
    public void setPresenter(OrderShopContract.Presenter presenter) {
        mPresenter = presenter;
        mPresenter.getListOrderManagementShop(mShopId);
    }

    @Override
    public void onGetListOrderManagementShopSuccess(List<Order> orders) {
        mOrders = orders;
        mOrderShopAdapter.updateData(orders);
    }

    @Override
    public void onGetListOrderManagementShopError(BaseException exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onAcceptOrRejectOrderManageSuccess() {
        onReLoadData();
    }

    @Override
    public void onAcceptOrRejectOrderManageError(Exception exception) {
        mNavigator.showToast(exception.getMessage());
    }

    @Override
    public void onReLoadData() {
        mPresenter.getListOrderManagementShop(mShopId);
    }

    @Override
    public void onShowProgressBarListOrder() {
        mProgressBarVisibilityListOrder.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressBarListOrder() {
        mProgressBarVisibilityListOrder.set(View.GONE);
    }

    public OrderShopAdapter getOrderShopAdapter() {
        return mOrderShopAdapter;
    }

    @Override
    public void onAcceptOrRejectOrderManager(
            OrderManagement acceptAndRejectOrdermanagementRequest) {
        mPresenter.acceptAndRejectOrder(mShopId, acceptAndRejectOrdermanagementRequest);
        onReLoadData();
    }

    public void onClickAcceptAllOrder() {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setShopId(mShopId);
        orderManagement.setStatus(mContext.getString(R.string.accepted_status));
        mPresenter.acceptAndRejectOrder(mShopId, orderManagement);
        onReLoadData();
    }

    public void onClickRejectAllOrder() {
        OrderManagement orderManagement = new OrderManagement();
        orderManagement.setShopId(mShopId);
        orderManagement.setStatus(mContext.getString(R.string.rejected_status));
        mPresenter.acceptAndRejectOrder(mShopId, orderManagement);
        onReLoadData();
    }

    public void onClickCloseOrder() {
        mNavigator.goNextChildFragment(R.id.layout_content,
                ListAcceptOrderFragment.newInstance(getListAcceptOrder(), mShopId), true,
                Navigator.RIGHT_LEFT, "ListAcceptOrderFragment");
    }

    private List<OrderDetail> getListAcceptOrder() {
        List<OrderDetail> orderAccepts = new ArrayList<>();
        for (Order order : mOrders) {
            checkItemAccept(order, orderAccepts);
        }
        return orderAccepts;
    }

    private void checkItemAccept(Order order, List<OrderDetail> orderAccepts) {
        List<OrderDetail> orderDetails = order.getOrderDetails();
        if (!isHaveStatusPending(orderDetails)) {
            checkAccept(orderDetails, orderAccepts);
        }
    }

    private void checkAccept(List<OrderDetail> orderDetails, List<OrderDetail> orderAccepts) {
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getStatusOrder() == Accepted) {
                addItemAcceptOrder(orderDetail, orderAccepts);
            }
        }
    }

    private void addItemAcceptOrder(OrderDetail orderDetail, List<OrderDetail> orderAccepts) {
        boolean isExist = false;
        for (OrderDetail orderAccept : orderAccepts) {
            if (orderAccept.getProductId() == orderDetail.getProductId()) {
                isExist = true;
                orderAccept.setQuantity(orderAccept.getQuantity() + orderDetail.getQuantity());
                break;
            }
        }
        if (!isExist) {
            orderAccepts.add(orderDetail);
        }
    }

    private boolean isHaveStatusPending(List<OrderDetail> orderDetails) {
        for (OrderDetail orderDetail : orderDetails) {
            if (orderDetail.getStatusOrder() == Pending) {
                return true;
            }
        }
        return false;
    }

    public ObservableField<Integer> getProgressBarVisibilityListOrder() {
        return mProgressBarVisibilityListOrder;
    }
}
