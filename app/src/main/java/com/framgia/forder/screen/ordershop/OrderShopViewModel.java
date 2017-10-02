package com.framgia.forder.screen.ordershop;

import android.content.Context;
import android.databinding.BaseObservable;
import android.databinding.ObservableField;
import android.support.annotation.IdRes;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.RadioGroup;
import com.framgia.forder.R;
import com.framgia.forder.data.model.Domain;
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
import static com.framgia.forder.data.model.OrderDetail.StatusOders.Rejected;

/**
 * Exposes the data to be used in the OrderShop screen.
 */

public class OrderShopViewModel extends BaseObservable
        implements OrderShopContract.ViewModel, OrderShopAdapter.OrderManagementListener {

    private static final String TAG = "OrderShopViewModel";

    private final Context mContext;
    private final Navigator mNavigator;
    private OrderShopContract.Presenter mPresenter;
    private final OrderShopAdapter mOrderShopAdapter;
    private final ShopManagement mShopManagement;
    private final int mShopId;
    private List<Order> mOrders;
    private final ObservableField<Integer> mProgressBarVisibilityListOrder =
            new ObservableField<>();
    private String mNameOrEmail;
    private final ArrayAdapter<String> mAdapterDomain;
    private int mSelectedPositionDomain;
    private final List<Domain> mDomains;
    private int mRadioButtonChecked;

    OrderShopViewModel(Context context, Navigator navigator, OrderShopAdapter orderShopAdapter,
            ShopManagement shopManagement, ArrayAdapter<String> adapterDomain) {
        mContext = context;
        mNavigator = navigator;
        mOrderShopAdapter = orderShopAdapter;
        mShopManagement = shopManagement;
        mAdapterDomain = adapterDomain;
        mOrderShopAdapter.setOrderManagementListener(this);
        mShopId = mShopManagement.getShop().getId();
        mOrders = new ArrayList<>();
        mDomains = new ArrayList<>();
        mProgressBarVisibilityListOrder.set(View.GONE);
        mRadioButtonChecked = R.id.rad_all;
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
        mNavigator.showToastCustom(exception.getMessage());
    }

    @Override
    public void onAcceptOrRejectOrderManageSuccess() {
        onReLoadData();
    }

    @Override
    public void onAcceptOrRejectOrderManageError(Exception exception) {
        Log.e(TAG, "onGetListOrderManagementShopError: ", exception);
    }

    @Override
    public void onReLoadData() {
        mPresenter.getListOrderManagementShopFilter(mShopId, mNameOrEmail,
                mSelectedPositionDomain == 0 ? "" : String.valueOf(getDomain().getId()),
                mRadioButtonChecked);
    }

    @Override
    public void onShowProgressBarListOrder() {
        mProgressBarVisibilityListOrder.set(View.VISIBLE);
    }

    @Override
    public void onHideProgressBarListOrder() {
        mProgressBarVisibilityListOrder.set(View.GONE);
    }

    @Override
    public void onGetListFilterSuccess(List<Order> orders, int idRadioButton) {
        mOrders = orders;
        if (idRadioButton == R.id.rad_all) {
            mOrderShopAdapter.updateData(orders);
            return;
        }
        if (idRadioButton == R.id.rad_pending) {
            mOrderShopAdapter.updateData(getListPendingOrder(orders));
            return;
        }
        if (idRadioButton == R.id.rad_accepted) {
            mOrderShopAdapter.updateData(getListAcceptOrder(orders));
            return;
        }
        mOrderShopAdapter.updateData(getListRejectOrder(orders));
    }

    @Override
    public void onGetListFilterError(BaseException error) {
        Log.e(TAG, "onGetListFilterError: ", error);
    }

    @Override
    public void onGetDomainSuccess(List<Domain> domains) {
        if (domains == null) {
            return;
        }
        mAdapterDomain.clear();
        mDomains.clear();
        mDomains.addAll(domains);
        mAdapterDomain.add(mContext.getString(R.string.all_category));
        for (Domain domain : domains) {
            mAdapterDomain.add(domain.getName());
        }
        mAdapterDomain.notifyDataSetChanged();
    }

    @Override
    public void onGetDomainError(BaseException error) {
        Log.e(TAG, "onGetDomainError: ", error);
    }

    @Override
    public void onRequestPaymentOrderSuccess() {
        mNavigator.showToastCustomActivity(R.string.update_success);
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

    @Override
    public void onPaidOrder(int orderId, boolean paid) {
        mPresenter.requestPaymentOrder(orderId, paid);
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

    public List<Order> getListPendingOrder(List<Order> orders) {
        List<Order> orderPendings = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                if (orderDetail.getStatusOrder() == Pending) {
                    orderDetails.add(orderDetail);
                }
            }
            if (orderDetails.size() != 0) {
                orderPendings.add(getOrder(orderDetails, order));
            }
        }
        return orderPendings;
    }

    public List<Order> getListAcceptOrder(List<Order> orders) {
        List<Order> orderAccepts = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                if (orderDetail.getStatusOrder() == Accepted) {
                    orderDetails.add(orderDetail);
                }
            }
            if (orderDetails.size() != 0) {
                orderAccepts.add(getOrder(orderDetails, order));
            }
        }
        return orderAccepts;
    }

    public List<Order> getListRejectOrder(List<Order> orders) {
        List<Order> orderRejects = new ArrayList<>();
        for (Order order : orders) {
            List<OrderDetail> orderDetails = new ArrayList<>();
            for (OrderDetail orderDetail : order.getOrderDetails()) {
                if (orderDetail.getStatusOrder() == Rejected) {
                    orderDetails.add(orderDetail);
                }
            }
            if (orderDetails.size() != 0) {
                orderRejects.add(getOrder(orderDetails, order));
            }
        }
        return orderRejects;
    }

    private Order getOrder(List<OrderDetail> orderDetails, Order order) {
        Order orderPending = new Order();
        orderPending.setOrderDetails(orderDetails);
        orderPending.setTotalPay(getTotalPrice(orderDetails));
        orderPending.setPaid(order.isPaid());
        orderPending.setId(order.getId());
        orderPending.setUserName(order.getUserName());
        orderPending.setTimeCreateOrder(order.getTimeCreateOrder());
        return orderPending;
    }

    public double getTotalPrice(List<OrderDetail> orderDetails) {
        double sum = 0;
        for (OrderDetail orderDetail : orderDetails) {
            sum += orderDetail.getTotalPrice();
        }
        return sum;
    }

    public RadioGroup.OnCheckedChangeListener getCheckChangeListerner() {
        return new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                switch (checkedId) {
                    case R.id.rad_all:
                        mRadioButtonChecked = R.id.rad_all;
                        mPresenter.getListOrderManagementShopFilter(mShopId, mNameOrEmail,
                                mSelectedPositionDomain == 0 ? ""
                                        : String.valueOf(getDomain().getId()), R.id.rad_all);
                        break;
                    case R.id.rad_pending:
                        mRadioButtonChecked = R.id.rad_pending;
                        mPresenter.getListOrderManagementShopFilter(mShopId, mNameOrEmail,
                                mSelectedPositionDomain == 0 ? ""
                                        : String.valueOf(getDomain().getId()), R.id.rad_pending);
                        break;
                    case R.id.rad_accepted:
                        mRadioButtonChecked = R.id.rad_accepted;
                        mPresenter.getListOrderManagementShopFilter(mShopId, mNameOrEmail,
                                mSelectedPositionDomain == 0 ? ""
                                        : String.valueOf(getDomain().getId()), R.id.rad_accepted);
                        break;
                    case R.id.rad_rejected:
                        mRadioButtonChecked = R.id.rad_rejected;
                        mPresenter.getListOrderManagementShopFilter(mShopId, mNameOrEmail,
                                mSelectedPositionDomain == 0 ? ""
                                        : String.valueOf(getDomain().getId()), R.id.rad_rejected);
                        break;
                    case R.id.rad_done:
                        mRadioButtonChecked = R.id.rad_done;
                        List<Order> emptyOrders = new ArrayList<>();
                        mOrderShopAdapter.updateData(emptyOrders);
                        break;
                    default:
                        break;
                }
            }
        };
    }

    public String getNameOrEmail() {
        if (mNameOrEmail != null) {
            return mNameOrEmail;
        }
        return "";
    }

    public void setNameOrEmail(String nameOrEmail) {
        mNameOrEmail = nameOrEmail;
    }

    public ArrayAdapter<String> getAdapterDomain() {
        return mAdapterDomain;
    }

    public int getSelectedPositionDomain() {
        return mSelectedPositionDomain;
    }

    public void setSelectedPositionDomain(int selectedPositionDomain) {
        mSelectedPositionDomain = selectedPositionDomain;
    }

    public Domain getDomain() {
        return mDomains.get(mSelectedPositionDomain - 1);
    }
}
