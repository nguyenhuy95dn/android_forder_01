package com.framgia.forder.screen.orderhistoryshop;

import android.app.DatePickerDialog;
import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import com.framgia.forder.BR;
import com.framgia.forder.utils.Utils;
import com.framgia.forder.widgets.dialog.DialogManager;
import java.util.Calendar;

/**
 * Exposes the data to be used in the OrderHistoryShop screen.
 */

public class OrderHistoryShopViewModel extends BaseObservable
        implements OrderHistoryShopContract.ViewModel, DatePickerDialog.OnDateSetListener {

    private static final int FLAG_DATE_FROM = 1;
    private static final int FLAG_DATE_TO = 2;

    private OrderHistoryShopContract.Presenter mPresenter;
    private final OrderHistoryPageAdapter mAdapter;
    private DialogManager mDialogManager;
    private Calendar mCalendar;
    private int mFlag;
    private String mDateFrom;
    private String mDateTo;
    private ArrayAdapter<String> mAdapterFillterBy;
    private int mSelectedPositionFillterBy;
    private String[] mFillters;

    OrderHistoryShopViewModel(OrderHistoryPageAdapter adapter, DialogManager dialogManager,
            ArrayAdapter<String> adapterFillterBy, String[] fillters) {
        mAdapter = adapter;
        mDialogManager = dialogManager;
        mAdapterFillterBy = adapterFillterBy;
        mCalendar = Calendar.getInstance();
        mDialogManager.dialogDatePicker(this);
        mFillters = fillters;
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
            mDateFrom = Utils.DateTimeUntils.convertDateToStringOther(mCalendar.getTime());
            notifyPropertyChanged(BR.dateFrom);
        } else {
            mDateTo = Utils.DateTimeUntils.convertDateToStringOther(mCalendar.getTime());
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

    public void setDateFrom(String dateFrom) {
        mDateFrom = dateFrom;
        notifyPropertyChanged(BR.dateFrom);
    }

    @Bindable
    public String getDateTo() {
        if (mDateTo != null) {
            return mDateTo;
        }
        return "";
    }

    public void setDateTo(String dateTo) {
        mDateTo = dateTo;
        notifyPropertyChanged(BR.dateTo);
    }

    public ArrayAdapter<String> getAdapterFillterBy() {
        return mAdapterFillterBy;
    }

    public int getSelectedPositionFillterBy() {
        return mSelectedPositionFillterBy;
    }

    public void setSelectedPositionFillterBy(int selectedPositionFillterBy) {
        mSelectedPositionFillterBy = selectedPositionFillterBy;
    }

    public void onClickSearch() {
    }
}
