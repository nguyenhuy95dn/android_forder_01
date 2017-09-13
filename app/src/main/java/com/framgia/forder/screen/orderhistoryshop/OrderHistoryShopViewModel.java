package com.framgia.forder.screen.orderhistoryshop;

import android.app.DatePickerDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.DatePicker;
import com.framgia.forder.BR;
import com.framgia.forder.data.model.OrderHistory;
import com.framgia.forder.data.model.ShopManagement;
import com.framgia.forder.data.source.remote.api.error.BaseException;
import com.framgia.forder.screen.orderhistoryshop.listdoneorders.ListDoneOrdersFragment;
import com.framgia.forder.screen.orderhistoryshop.listrejectorders.ListRejectOrdersFragment;
import com.framgia.forder.utils.Utils;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.Calendar;
import java.util.List;

/**
 * Exposes the data to be used in the OrderHistoryShop screen.
 */

public class OrderHistoryShopViewModel extends BaseObservable
        implements OrderHistoryShopContract.ViewModel, DatePickerDialog.OnDateSetListener {

    private static final String TAG = "OrderHistoryShop";

    private static final int FLAG_DATE_FROM = 1;
    private static final int FLAG_DATE_TO = 2;

    private OrderHistoryShopContract.Presenter mPresenter;
    private final OrderHistoryPageAdapter mAdapter;
    private final DialogManager mDialogManager;
    private final Calendar mCalendar;
    private int mFlag;
    private String mDateFrom;
    private String mDateTo;
    private boolean mIsShowFilter;
    private final ShopManagement mShopManagement;

    OrderHistoryShopViewModel(OrderHistoryPageAdapter adapter, DialogManager dialogManager,
            ShopManagement shopManagement) {
        mAdapter = adapter;
        mDialogManager = dialogManager;
        mCalendar = Calendar.getInstance();
        mDialogManager.dialogDatePicker(this);
        mShopManagement = shopManagement;
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
    public void setPresenter(OrderHistoryShopContract.Presenter presenter) {
        mPresenter = presenter;
    }

    public OrderHistoryPageAdapter getAdapter() {
        return mAdapter;
    }

    public void onClickChooseDateFrom() {
        mFlag = FLAG_DATE_FROM;
        mDialogManager.showDatePickerDialog();
    }

    public void onClickChooseDateTo() {
        mFlag = FLAG_DATE_TO;
        mDialogManager.showDatePickerDialog();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        mCalendar.set(Calendar.YEAR, year);
        mCalendar.set(Calendar.MONTH, month);
        mCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
        if (mFlag == FLAG_DATE_FROM) {
            mDateFrom = Utils.DateTimeUntils.convertDateToString(mCalendar.getTime());
            notifyPropertyChanged(BR.dateFrom);
        } else {
            mDateTo = Utils.DateTimeUntils.convertDateToString(mCalendar.getTime());
            notifyPropertyChanged(BR.dateTo);
        }
    }

    @Bindable
    public String getDateFrom() {
        if (mDateFrom != null) {
            return mDateFrom;
        }
        return "";
    }

    @Bindable
    public String getDateTo() {
        if (mDateTo != null) {
            return mDateTo;
        }
        return "";
    }

    public void onClickSearch() {
        mPresenter.getListOrdersShopFilter(mShopManagement.getShop().getId(), mDateFrom, mDateTo);
    }

    public void onClickShowFilter() {
        mIsShowFilter = !mIsShowFilter;
        notifyPropertyChanged(BR.showFilter);
    }

    @Bindable
    public boolean isShowFilter() {
        return mIsShowFilter;
    }

    @Override
    public void onFilterListDoneOrderSuccess(List<OrderHistory> listDoneOrders) {
        Fragment fragment = mAdapter.getFragment(
                OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_REJECT);
        ((ListDoneOrdersFragment) fragment).setOrders(listDoneOrders);
    }

    @Override
    public void onFilterListRejectOrdeSuccess(List<OrderHistory> listRejectedOrder) {
        Fragment fragment = mAdapter.getFragment(
                OrderHistoryPageAdapter.OrderHistoryPageResultsTab.TAB_ORDER_ACCEPTED);
        ((ListRejectOrdersFragment) fragment).setOrders(listRejectedOrder);
    }

    @Override
    public void onFilterListOrderError(BaseException error) {
        Log.e(TAG, "onFilterListOrderError: ", error);
    }
}
